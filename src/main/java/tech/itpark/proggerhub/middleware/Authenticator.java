package tech.itpark.proggerhub.middleware;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Component;
import tech.itpark.proggerhub.security.AuthProvider;
import tech.itpark.servlet.Middleware;

@Component
@RequiredArgsConstructor
public class Authenticator implements Middleware {
  protected final Log logger = LogFactory.getLog(getClass());
  private final AuthProvider provider;
  // true - я обработал, дальше обрабатывать не надо
  @Override
  public boolean handle(
      HttpServletRequest request,
      HttpServletResponse response
  ) {
    final var token = request.getHeader("Authorization");
    final var auth = provider.authenticate(token);
    // 1. Singleton (not work)
    // AuthenticationHolder.getInstance().setAuth(auth);
    // 2. ThreadLocal
    request.setAttribute("AUTH", auth);

    return false;
  }
}
