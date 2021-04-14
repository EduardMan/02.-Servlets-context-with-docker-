package tech.itpark.servlet;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public interface Middleware {
  // true - я обработал, дальше обрабатывать не надо
  boolean handle(
      HttpServletRequest request,
      HttpServletResponse response
  );
}
