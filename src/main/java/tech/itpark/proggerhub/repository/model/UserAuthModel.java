package tech.itpark.proggerhub.repository.model;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Set;

@AllArgsConstructor
@Data
public class UserAuthModel {
  private long id;
  private Set<String> roles;
}
