package com.tistory.amyyzzin.trvl.exception;

public abstract class AbstractException extends RuntimeException {

    abstract public int getStatusCode();

    abstract public String getMessage();
}
