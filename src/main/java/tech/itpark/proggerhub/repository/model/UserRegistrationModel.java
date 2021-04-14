package tech.itpark.proggerhub.repository.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import tech.itpark.proggerhub.enums.RestoreQuestion;

@AllArgsConstructor
@Data
public class UserRegistrationModel {
  private String login;
  private String hash;

  private RestoreQuestion restoreQuestion;
  private String hashedRestoreAnswer;
}
