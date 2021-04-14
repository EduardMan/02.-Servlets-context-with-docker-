package tech.itpark.proggerhub.controller;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import tech.itpark.proggerhub.converter.BodyConverter;
import tech.itpark.proggerhub.dto.ErrorDto;
import tech.itpark.proggerhub.dto.UserIdDto;
import tech.itpark.proggerhub.errorcodes.Codes;
import tech.itpark.servlet.ContentTypes;
import tech.itpark.servlet.ErrorController;

@Controller
@RequiredArgsConstructor
public class AppErrorController implements ErrorController {
  private final BodyConverter converter; // TODO: list of body converters

  @Override
  public void notFound(HttpServletRequest request, HttpServletResponse response) {
//    ErrorController.super.notFound(request, response);
    try {
      response.setStatus(404);
      response.addHeader("Content-Type", ContentTypes.APPLICATION_JSON);
      converter.write(response.getWriter(), ErrorDto.notFound());
    } catch (Exception e) {
      e.printStackTrace();
    }
  }
}
