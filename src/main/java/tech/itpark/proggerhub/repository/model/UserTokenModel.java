package tech.itpark.proggerhub.repository.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class UserTokenModel {
  private long id;
  private String token;
}
