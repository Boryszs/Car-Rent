package com.Server.exception;

/**
 * Exception to wrong request.
 * @author Krystian Cwioro Kamil Bieniasz Damian Mierzynski.
 * @version 2.0.
 * @since 2020-04-27.
 */

public class WrongDataException extends java.lang.Exception {
    /**error*/
    private String error;

    /**Constructor*/
    public WrongDataException(String error) {
        this.error = error;
    }

    /**Constructor*/
    public WrongDataException(String s, String error) {
        super(s);
        this.error = error;
    }

    /**Constructor*/
    public WrongDataException(String s, Throwable throwable, String error) {
        super(s, throwable);
        this.error = error;
    }

    /**Constructor*/
    public WrongDataException(Throwable throwable, String error) {
        super(throwable);
        this.error = error;
    }

    /**Constructor*/
    public WrongDataException(String s, Throwable throwable, boolean b, boolean b1, String error) {
        super(s, throwable, b, b1);
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
