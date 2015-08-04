package com.baselogic.tutorials.domain.validation;

import javax.validation.ConstraintViolation;
import javax.validation.Validator;
import java.util.Set;

public class ValidationUtil {

    public static void validateAndThrow(Validator validator, Object objectToValidate) {
        Set<ConstraintViolation<Object>> violations = validator.validate(objectToValidate);
        if (!violations.isEmpty()) {
            throw new ValidationException(violations);
        }
    }
}
