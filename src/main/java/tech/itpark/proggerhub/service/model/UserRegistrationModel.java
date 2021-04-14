package tech.itpark.proggerhub.service.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import tech.itpark.proggerhub.enums.RestoreQuestion;

@AllArgsConstructor
@Data
public class UserRegistrationModel {
  private String login;
  private String password;

  private RestoreQuestion restoreQuestion;
  private String restoreAnswer;
}
