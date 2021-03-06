package tech.itpark.proggerhub.dto;

import lombok.Data;
import tech.itpark.proggerhub.enums.RestoreQuestion;

@Data
public class UserDto {
  private String login;
  private String password;

  private RestoreQuestion restoreQuestion;
  private String restoreAnswer;
}
