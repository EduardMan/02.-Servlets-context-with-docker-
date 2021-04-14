package tech.itpark.servlet;

import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.UnavailableException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.io.IOException;
import java.util.Collection;
import java.util.Map;
import java.util.Optional;

// Servlet -> Singleton
public class MainServlet extends HttpServlet {
  private Map<String, Map<String, Handler>> routes;
  private ErrorController errorCtrl;

  @Override
  public void init(ServletConfig config) throws ServletException {
    super.init(config);
    try {
      final var context = (ConfigurableApplicationContext) getServletContext().getAttribute("CONTEXT");
      errorCtrl = context.getBean(ErrorController.class);
      routes = (Map<String, Map<String, Handler>>) context.getBean("routes");
    } catch (Exception e) {
      e.printStackTrace();
      throw new UnavailableException(e.getMessage());
    }
  }

  @Override
  protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    Optional.ofNullable(routes.get(req.getServletPath()))
        .map(o -> o.getOrDefault(req.getMethod(), errorCtrl::methodNotAllowed))
        .orElse(errorCtrl::notFound)
        .handle(req, resp)
        ;
  }
}
