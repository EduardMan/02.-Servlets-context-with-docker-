package tech.itpark.proggerhub.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import tech.itpark.proggerhub.errorcodes.Codes;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class ErrorDto {
  private String code; // err.login_invalid -> JSON (information disclosure)

  public static ErrorDto notFound() {
    return new ErrorDto(Codes.NOT_FOUND);
  }
}
