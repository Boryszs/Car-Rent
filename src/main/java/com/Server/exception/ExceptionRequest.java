package com.Server.exception;

public class ExceptionRequest extends Exception {
    private String err;

    public ExceptionRequest(String err) {
        this.err = err;
    }

    public ExceptionRequest(String s, String err) {
        super(s);
        this.err = err;
    }

    public ExceptionRequest(String s, Throwable throwable, String err) {
        super(s, throwable);
        this.err = err;
    }

    public ExceptionRequest(Throwable throwable, String err) {
        super(throwable);
        this.err = err;
    }

    public ExceptionRequest(String s, Throwable throwable, boolean b, boolean b1, String err) {
        super(s, throwable, b, b1);
        this.err = err;
    }

    public String getErr() {
        return err;
    }
}
