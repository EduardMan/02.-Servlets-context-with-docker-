package tech.itpark.proggerhub.exception;

public class PasswordsNotMatchedException extends RuntimeException {
  public PasswordsNotMatchedException() {
    super();
  }

  public PasswordsNotMatchedException(String message) {
    super(message);
  }

  public PasswordsNotMatchedException(String message, Throwable cause) {
    super(message, cause);
  }

  public PasswordsNotMatchedException(Throwable cause) {
    super(cause);
  }

  protected PasswordsNotMatchedException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
    super(message, cause, enableSuppression, writableStackTrace);
  }
}
