package tech.itpark.proggerhub.dto;

import lombok.Data;
import tech.itpark.proggerhub.enums.RestoreQuestion;

@Data
public class UserRestoreDto {
  private String login;
  private String newPassword;

  private String restoreAnswer;
}
