package tech.itpark.proggerhub.exception;

public class RestorePasswordException extends RuntimeException {
    public RestorePasswordException() {
        super();
    }

    public RestorePasswordException(String message) {
        super(message);
    }

    public RestorePasswordException(String message, Throwable cause) {
        super(message, cause);
    }

    public RestorePasswordException(Throwable cause) {
        super(cause);
    }

    protected RestorePasswordException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
