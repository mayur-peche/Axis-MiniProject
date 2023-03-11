package com.mal.exception;

import java.util.Set;
import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;

public class CustomConstraintViolationException extends ConstraintViolationException {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public CustomConstraintViolationException(String message, Set<ConstraintViolation<?>> violations) {
        super(message, violations);
    }
}
