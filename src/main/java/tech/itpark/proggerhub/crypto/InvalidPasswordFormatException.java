package tech.itpark.proggerhub.crypto;

public class InvalidPasswordFormatException extends RuntimeException {
  public InvalidPasswordFormatException() {
    super();
  }

  public InvalidPasswordFormatException(String message) {
    super(message);
  }

  public InvalidPasswordFormatException(String message, Throwable cause) {
    super(message, cause);
  }

  public InvalidPasswordFormatException(Throwable cause) {
    super(cause);
  }

  protected InvalidPasswordFormatException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
    super(message, cause, enableSuppression, writableStackTrace);
  }
}
