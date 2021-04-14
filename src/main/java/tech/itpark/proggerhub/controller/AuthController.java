package tech.itpark.proggerhub.controller;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import tech.itpark.proggerhub.converter.BodyConverter;
import tech.itpark.proggerhub.dto.*;
import tech.itpark.proggerhub.security.Authentication;
import tech.itpark.proggerhub.service.AuthService;
import tech.itpark.proggerhub.service.model.UserModel;
import tech.itpark.proggerhub.service.model.UserRegistrationModel;
import tech.itpark.proggerhub.service.model.UserRestoreModel;
import tech.itpark.servlet.ContentTypes;

import java.io.IOException;

@Controller
@RequiredArgsConstructor
public class AuthController {
  private final AuthService service;
  private final BodyConverter converter; // TODO: list of body converters

  // register
  public void register(HttpServletRequest request, HttpServletResponse response) {
    try {
      if (!converter.canRead(request.getHeader("Content-Type"), UserDto.class)) {
        response.sendError(415, "media type not supported");
        return;
      }

      final var dto = converter.read(request.getReader(), UserDto.class);
      final var id = service.register(new UserRegistrationModel(dto.getLogin(), dto.getPassword(), dto.getRestoreQuestion(), dto.getRestoreAnswer()));
      // TODO: converter can write
      response.addHeader("Content-Type", ContentTypes.APPLICATION_JSON);
      converter.write(response.getWriter(), new UserIdDto(id));
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  public void login(HttpServletRequest request, HttpServletResponse response) {
    try {
      if (!converter.canRead(request.getHeader("Content-Type"), UserDto.class)) {
        response.sendError(415, "media type not supported");
        return;
      }

      // TODO: handle business exceptions
      final var dto = converter.read(request.getReader(), UserDto.class);
      final var token = service.login(new UserModel(dto.getLogin(), dto.getPassword()));

      response.addHeader("Content-Type", ContentTypes.APPLICATION_JSON);
      converter.write(response.getWriter(), new UserTokenDto(token));
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  // 1. ADMIN
  public void removeById(HttpServletRequest request, HttpServletResponse response) {
    final var auth = (Authentication) request.getAttribute("AUTH");
    service.removeById(auth);
  }

  public void restore(HttpServletRequest request, HttpServletResponse response) {
    try {
      if (!converter.canRead(request.getHeader("Content-Type"), UserDto.class)) {
        response.sendError(415, "media type not supported");
        return;
      }

      final UserRestoreDto dto = converter.read(request.getReader(), UserRestoreDto.class);
      service.restoreUser(new UserRestoreModel(dto.getLogin(), dto.getNewPassword(), dto.getRestoreAnswer()));
      converter.write(response.getWriter(), new ResponseMessageDto("Successful recovery"));
    } catch (IOException e) {
      e.printStackTrace();
    }
  }
}
