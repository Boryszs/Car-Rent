package com.Server.exception;

/**
 * Exception to wrong request.
 * @author Krystian Cwioro Kamil Bieniasz Damian Mierzynski.
 * @version 1.0
 * @since 2020-12-29.
 */

public class ExceptionRequest extends Exception {
    /**err*/
    private String err;

    /**Constructor*/
    public ExceptionRequest(String err) {
        this.err = err;
    }

    /**Constructor*/
    public ExceptionRequest(String s, String err) {
        super(s);
        this.err = err;
    }

    /**Constructor*/
    public ExceptionRequest(String s, Throwable throwable, String err) {
        super(s, throwable);
        this.err = err;
    }

    /**Constructor*/
    public ExceptionRequest(Throwable throwable, String err) {
        super(throwable);
        this.err = err;
    }

    /**Constructor*/
    public ExceptionRequest(String s, Throwable throwable, boolean b, boolean b1, String err) {
        super(s, throwable, b, b1);
        this.err = err;
    }

    /**
     *
     * @return message error.
     */
    public String getErr() {
        return err;
    }
}
