package tech.itpark.proggerhub.repository;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import tech.itpark.proggerhub.exception.DataAccessException;
import tech.itpark.proggerhub.repository.model.*;

import javax.sql.DataSource;
import java.sql.SQLException;
import java.util.Optional;
import java.util.Set;

@Repository
@RequiredArgsConstructor
public class AuthRepository {
  private final DataSource ds;

  public long save(UserRegistrationModel model) {
    try (
        final var conn = ds.getConnection();
        final var stmt = conn.prepareStatement(
            "INSERT INTO users(login, password, restore_question, restore_answer) VALUES(?, ?, ?, ?) RETURNING id;"
        );
    ) {
      var index = 0;
      stmt.setString(++index, model.getLogin());
      stmt.setString(++index, model.getHash());
      stmt.setString(++index, model.getRestoreQuestion().toString());
      stmt.setString(++index, model.getHashedRestoreAnswer());
      try (
          final var rs = stmt.executeQuery();
      ) {
        if (!rs.next()) {
          throw new DataAccessException("no returning id");
        }
        return rs.getLong(1);
      }
    } catch (SQLException e) {
      throw new DataAccessException(e);
    }
  }

  /**
   * SQLi sample
   *
   * @param login - vulnerable login string
   * @return user model
   * <p>
   * Sample of malicious input (for login as admin):
   * <p>
   * POST http://localhost:8080/auth/login
   * Content-Type: application/json
   * <p>
   * {
   * "login": "' AND 1<>1 UNION SELECT 1, 'admin', 'fa0bbaad89e4440ed7ad2f11e7b33f73c7589ea86823182e69671ca13216c23241ca397cf50f2df48d5ded068a6c2eecb8e2db23515df1faf5f8cd64eda44d2a:16c0462fc909e426d9415b0f06e55ae8c12b50976f533fd4cd46268170d6af43' -- ;",
   * "password": "password"
   * }
   * <p>
   * > {%
   * client.test("Request executed successfully", function() {
   * client.assert(response.status === 200, "Response status is not 200");
   * });
   * %}
   */
  public Optional<UserWithIdModel> findByLogin(String login) {
    try (
        final var conn = ds.getConnection();
        final var stmt = conn.createStatement();
        final var rs = stmt.executeQuery("SELECT id, login, password, restore_answer FROM users WHERE login = '" + login + "'");
    ) {
      return rs.next() ? Optional.of(
          new UserWithIdModel(rs.getLong("id"), rs.getString("login"), rs.getString("password"), rs.getString("restore_answer"))
      ) : Optional.empty();
    } catch (SQLException e) {
      throw new DataAccessException(e);
    }
  }

  public void save(UserTokenModel model) {
    try (
        final var conn = ds.getConnection();
        final var stmt = conn.prepareStatement(
            "INSERT INTO tokens(user_id, token) VALUES(?, ?);"
        );
    ) {
      var index = 0;
      stmt.setLong(++index, model.getId());
      stmt.setString(++index, model.getToken());
      stmt.executeUpdate();
    } catch (SQLException e) {
      throw new DataAccessException(e);
    }
  }

  public Optional<UserAuthModel> findByToken(String token) {
    try (
        final var conn = ds.getConnection();
        final var stmt = conn.prepareStatement(
            // FIXME: вопросы инвалидации (кто-то должен вычищать старые токены)
            """
                  SELECT u.id, u.roles
                  FROM users u
                  JOIN tokens t on u.id = t.user_id
                  WHERE t.token = ?;
                """
        );
    ) {
      int index = 0;
      stmt.setString(++index, token);
      try (
          final var rs = stmt.executeQuery();
          ) {
        return !rs.next() ? Optional.empty() : Optional.of(
            new UserAuthModel(
                rs.getLong("id"),
                Set.of((String[]) rs.getArray("roles").getArray())
            )
        );
      }
    } catch (SQLException e) {
      throw new DataAccessException(e);
    }
  }

  public void updatePassword(UserRestoreModel model) {
    try (
        final var conn = ds.getConnection();
        final var stmt = conn.prepareStatement(
                "UPDATE users SET password = ? WHERE login = ?"
        );
    ) {
      var index = 0;
      stmt.setString(++index, model.getHash());
      stmt.setString(++index, model.getLogin());
      stmt.executeUpdate();
    } catch (SQLException e) {
      throw new DataAccessException(e);
    }
  }
}
