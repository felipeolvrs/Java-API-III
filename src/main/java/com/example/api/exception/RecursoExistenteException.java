package com.example.api.exception;

public class RecursoExistenteException extends RuntimeException {
    public RecursoExistenteException(String message) {
        super(message);
    }

    public RecursoExistenteException() {
        super("Recurso já existente no sistema.");
    }

}


