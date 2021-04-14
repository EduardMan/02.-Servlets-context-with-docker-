package tech.itpark.proggerhub.service.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import tech.itpark.proggerhub.security.Authentication;

import java.util.Set;

@AllArgsConstructor
@Data
public class UserAuthModel implements Authentication {
  private long id;
  private Set<String> roles;
}