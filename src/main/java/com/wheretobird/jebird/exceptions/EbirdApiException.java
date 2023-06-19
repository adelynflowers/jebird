package com.wheretobird.jebird.exceptions;

public class EbirdApiException extends Exception {
    public EbirdApiException() {

    }

    public EbirdApiException(String message) {
        super(message);
    }

    public EbirdApiException(Throwable cause) {
        super(cause);
    }

    public EbirdApiException(String message, Throwable cause) {
        super(message, cause);
    }
}
