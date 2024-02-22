package br.com.mybet.domain.core.entity.exception;

public class DomainException extends RuntimeException {

    public DomainException(final String message) {
        super(message);
    }
}
