package com.example.api.exception;

public class RegraNegocioException extends RuntimeException {
    public RegraNegocioException(String message) {
        super(message);
    }

    public RegraNegocioException() {
        super("A requisição viola uma regra de negócio.");
    }
}