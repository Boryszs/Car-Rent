package com.Server.exception;

/**
 * Exception to wrong request.
 * @author Krystian Cwioro Kamil Bieniasz Damian Mierzynski.
 * @version 2.0.
 * @since 2020-04-27.
 */

public class WrongDataException extends RuntimeException {
    /**error*/
    private String error;

    /**
     * Constructor
     * @param error
     */
    public WrongDataException(String error) {
        super(error);
        this.error = error;
    }

    /**
     * Constructor
     * @param error
     */
    public WrongDataException(String message, String error) {
        super(message);
        this.error = error;
    }

    /**
     * Constructor
     * @param error
     */
    public WrongDataException(String message, Throwable cause, String error) {
        super(message, cause);
        this.error = error;
    }

    /**
     * Constructor
     * @param error
     */
    public WrongDataException(Throwable cause, String error) {
        super(cause);
        this.error = error;
    }

    /**
     * Constructor
     * @param error
     */
    public WrongDataException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace, String error) {
        super(message, cause, enableSuppression, writableStackTrace);
        this.error = error;
    }

    /**
     *
     * @return message error.
     */
    public String getError() {
        return error;
    }
}
