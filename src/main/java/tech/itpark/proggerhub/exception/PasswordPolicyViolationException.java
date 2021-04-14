package tech.itpark.proggerhub.exception;

public class PasswordPolicyViolationException extends RuntimeException {
  public PasswordPolicyViolationException() {
    super();
  }

  public PasswordPolicyViolationException(String message) {
    super(message);
  }

  public PasswordPolicyViolationException(String message, Throwable cause) {
    super(message, cause);
  }

  public PasswordPolicyViolationException(Throwable cause) {
    super(cause);
  }

  protected PasswordPolicyViolationException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
    super(message, cause, enableSuppression, writableStackTrace);
  }
}
