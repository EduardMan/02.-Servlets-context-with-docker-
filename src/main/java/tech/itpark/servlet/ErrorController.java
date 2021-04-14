package tech.itpark.servlet;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

public interface ErrorController {
  default void notFound(HttpServletRequest request, HttpServletResponse response) {
    sendError(request, response, 404, "Not Found");
  }

  default void methodNotAllowed(HttpServletRequest request, HttpServletResponse response) {
    sendError(request, response, 405, "Method Not Allowed");
  }

  private void sendError(HttpServletRequest request, HttpServletResponse response, int code, String message) {
    try {
      response.sendError(code, message);
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
  }
}
