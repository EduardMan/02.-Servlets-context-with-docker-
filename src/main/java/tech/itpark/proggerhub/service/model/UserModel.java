package tech.itpark.proggerhub.service.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class UserModel {
  private String login;
  private String password;
}
