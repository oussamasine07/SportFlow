package com.sportflow.dto;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class PasswordMatchValidator implements ConstraintValidator<PasswordMatch, RegisterDTO> {
    @Override
    public void initialize(PasswordMatch constraintAnnotation) {
        // No initialization needed
    }

    @Override
    public boolean isValid(RegisterDTO registerDTO, ConstraintValidatorContext context) {
        System.out.println(registerDTO.getPassword());
        if (registerDTO == null) {
            return true; // Ignore validation if the object is null
        }

        if (!registerDTO.getPassword().equals(registerDTO.getConfirmPassword())) {
            // ⚠️ This makes the error appear under "confirmPassword" instead of the class
            context.disableDefaultConstraintViolation();
            context.buildConstraintViolationWithTemplate("Passwords do not match")
                    .addPropertyNode("confirmPassword") // Associate error with confirmPassword field
                    .addConstraintViolation();

            return false;
        }
        return true;
    }
}