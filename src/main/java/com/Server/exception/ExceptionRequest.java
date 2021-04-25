package com.Server.exception;

/**
 * Exception to wrong request.
 * @author Krystian Cwioro Kamil Bieniasz Damian Mierzynski.
 * @version 1.0
 * @since 2020-12-29.
 */

public class ExceptionRequest extends Exception {
    /**err*/
    private String error;

    /**Constructor*/
    public ExceptionRequest(String error) {
        this.error = error;
    }

    /**Constructor*/
    public ExceptionRequest(String s, String error) {
        super(s);
        this.error = error;
    }

    /**Constructor*/
    public ExceptionRequest(String s, Throwable throwable, String error) {
        super(s, throwable);
        this.error = error;
    }

    /**Constructor*/
    public ExceptionRequest(Throwable throwable, String error) {
        super(throwable);
        this.error = error;
    }

    /**Constructor*/
    public ExceptionRequest(String s, Throwable throwable, boolean b, boolean b1, String error) {
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
