package tech.itpark.proggerhub.security;

public class AuthenticationHolder {
  public static final AuthenticationHolder instance = new AuthenticationHolder();
  private Authentication auth;
  private AuthenticationHolder() {}

  public static AuthenticationHolder getInstance() {
    return instance;
  }

  public Authentication getAuth() {
    return auth;
  }

  public void setAuth(Authentication auth) {
    this.auth = auth;
  }
}
