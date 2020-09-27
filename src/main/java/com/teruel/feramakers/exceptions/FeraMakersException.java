package com.teruel.feramakers.exceptions;

public class FeraMakersException extends RuntimeException {

    public FeraMakersException(String exMessage, Exception exception) {
        super(exMessage, exception);
    }

    public FeraMakersException(String exMessage) {
        super(exMessage);
    }
}
