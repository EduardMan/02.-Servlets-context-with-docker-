package tech.itpark.servlet;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@FunctionalInterface
public interface Handler {
  void handle(HttpServletRequest request, HttpServletResponse response);
}
