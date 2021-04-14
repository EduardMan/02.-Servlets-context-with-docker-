package tech.itpark.proggerhub.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import tech.itpark.proggerhub.controller.AuthController;
import tech.itpark.servlet.ErrorController;
import tech.itpark.servlet.Handler;
import tech.itpark.servlet.HttpMethods;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Map;

@Configuration
public class ApplicationConfiguration {
  @Bean
  public MessageDigest messageDigest() throws NoSuchAlgorithmException {
    return MessageDigest.getInstance("SHA-256");
  }

  @Bean
  public DataSource dataSource() throws NamingException {
    final var context = new InitialContext();
    return  (DataSource) context.lookup("java:/comp/env/jdbc/db");
  }
//
//  @Bean("defaultCtrl") // для разнообразия - можно было переименовать метод
//  public ErrorController defaultController() {
//    return new ErrorController();
//  }

  @Bean
  public Map<String, Map<String, Handler>> routes(AuthController ctrl) {
    return Map.of(
        "/auth/register", Map.of(HttpMethods.POST, ctrl::register),
        "/auth/login", Map.of(HttpMethods.POST, ctrl::login),
        // TODO: secret word
        "/auth/restore", Map.of(HttpMethods.POST, ctrl::restore),
        "/auth/remove", Map.of(HttpMethods.DELETE, ctrl::removeById)
    );
  }
}
