package tech.itpark.proggerhub.repository.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import tech.itpark.proggerhub.enums.RestoreQuestion;

@AllArgsConstructor
@Data
public class UserRestoreModel {
  private String login;
  private String hash;
}
