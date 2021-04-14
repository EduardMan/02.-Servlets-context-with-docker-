package tech.itpark.proggerhub.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import tech.itpark.proggerhub.crypto.PasswordHasher;
import tech.itpark.proggerhub.crypto.TokenGenerator;
import tech.itpark.proggerhub.exception.*;
import tech.itpark.proggerhub.repository.AuthRepository;
import tech.itpark.proggerhub.repository.model.UserTokenModel;
import tech.itpark.proggerhub.repository.model.UserWithIdModel;
import tech.itpark.proggerhub.security.AuthProvider;
import tech.itpark.proggerhub.security.Authentication;
import tech.itpark.proggerhub.service.model.UserAuthModel;
import tech.itpark.proggerhub.service.model.UserModel;
import tech.itpark.proggerhub.service.model.UserRegistrationModel;
import tech.itpark.proggerhub.service.model.UserRestoreModel;

@Service
@RequiredArgsConstructor
public class AuthService implements AuthProvider {
  private final AuthRepository repository;
  private final PasswordHasher hasher;
  private final TokenGenerator tokenGenerator;

  // TODO: id? -> long, UUID
  // TODO: кто должен делать вычистку данных? Validator vs RichModel
  public long register(UserRegistrationModel model) {
    if (model.getLogin() == null) {
      throw new BadLoginException();
    }

    // TODO: Regex -> 2 проблемы
    // TODO: whitelisting, лучше всё запретить и разрешить только что нужно
    if (!model.getLogin().matches("^[a-zA-Z0-9]{3,15}$")) {
      throw new BadLoginException();
    }
    // TODO: blacklisting: false positive (грабля), неполнота

    // 1. Проверить свободен ли логин -> TODO: распарсить код ответа от БД: 23505?
    // 2. Проверка пароля?
    // 3. Зашифровать (захешировать) -> TODO: fix
    // 4. Сохранить
    validatePassword(model.getPassword());

    // TODO: IB -> взламывать дорого -> hashed
    // md5 not secure <- sha2, ...
    return repository.save(new tech.itpark.proggerhub.repository.model.UserRegistrationModel(model.getLogin().trim().toLowerCase(),
        hasher.hash(model.getPassword()), model.getRestoreQuestion(), hasher.hash(model.getRestoreAnswer())
    ));
  }

  public String login(UserModel model) {
    final var user = repository.findByLogin(model.getLogin()).orElseThrow(UserNotFoundException::new);

    if (!hasher.match(user.getHash(), model.getPassword())) {
      throw new PasswordsNotMatchedException();
    }

    final var token = tokenGenerator.generate();
    repository.save(new UserTokenModel(user.getId(), token));
    return token;
  }

  public Authentication authenticate(String token) {
    // TODO: look before you jump
    // TODO: try, then sorry
    return repository.findByToken(token)
        .map(o -> (Authentication) new UserAuthModel(o.getId(), o.getRoles()))
        .orElse(Authentication.anonymous())
        ;
  }

  // security:
  // TODO: 1. Security - часть БЛ
  // TODO: 2. Security - cross concern stuff
  public void removeById(Authentication auth) {
    if (!auth.hasAnyRoles("ROLE_ADMIN")) {
      throw new PermissionDeniedException();
    }
    // ok
  }

  public void restoreUser(UserRestoreModel model) {
    final UserWithIdModel user = repository.findByLogin(model.getLogin()).orElseThrow(UserNotFoundException::new);
    if (!hasher.match(user.getRestoreAnswer(), model.getRestoreAnswer())) {
      throw new RestorePasswordException("Restore answer is wrong");
    }

    validatePassword(model.getNewPassword());

    repository.updatePassword(new tech.itpark.proggerhub.repository.model.UserRestoreModel(model.getLogin(), hasher.hash(model.getNewPassword())));
  }

  private void validatePassword(String password) {
    if (password.length() < 8) {
      throw new PasswordPolicyViolationException("must be longer than 8");
    }
  }
}
