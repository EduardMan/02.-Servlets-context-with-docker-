package tech.itpark.proggerhub.exception;

public class BadLoginException extends RuntimeException {
  public BadLoginException() {
    super();
  }

  public BadLoginException(String message) {
    super(message);
  }

  public BadLoginException(String message, Throwable cause) {
    super(message, cause);
  }

  public BadLoginException(Throwable cause) {
    super(cause);
  }

  protected BadLoginException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
    super(message, cause, enableSuppression, writableStackTrace);
  }
}
