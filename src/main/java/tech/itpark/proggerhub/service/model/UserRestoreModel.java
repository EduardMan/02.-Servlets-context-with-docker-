package tech.itpark.proggerhub.service.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class UserRestoreModel {
  private String login;
  private String newPassword;

  private String restoreAnswer;
}
