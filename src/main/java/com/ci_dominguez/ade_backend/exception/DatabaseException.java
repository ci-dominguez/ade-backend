package com.ci_dominguez.ade_backend.exception;

public class DatabaseException extends RuntimeException{
    public DatabaseException(String message, Throwable cause){
        super(message, cause);
    }
}
