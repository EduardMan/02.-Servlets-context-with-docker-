package tech.itpark.proggerhub.security;

public interface AuthProvider {
  Authentication authenticate(String token);
}
