package tech.itpark.proggerhub.exception;

public class ConversionException extends RuntimeException {
  public ConversionException() {
    super();
  }

  public ConversionException(String message) {
    super(message);
  }

  public ConversionException(String message, Throwable cause) {
    super(message, cause);
  }

  public ConversionException(Throwable cause) {
    super(cause);
  }

  protected ConversionException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
    super(message, cause, enableSuppression, writableStackTrace);
  }
}
