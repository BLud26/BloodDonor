package com.ben;

import org.springframework.validation.FieldError;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ErrorUtils {

    public static Map<String, String> createErrorMap(List<FieldError> errorList) {
        Map<String, String> validationErrorMap = new HashMap<>();
        for (FieldError error : errorList) {
            System.out.println("fieldError " + error);
            String fieldName = error.getField();

            String errorMessage = error.getDefaultMessage();
            if (error.getCodes() != null && List.of(error.getCodes()).contains("typeMismatch.int")) {
                errorMessage = "Enter only integer values"; // Replace with more user-friendly message
            }
            validationErrorMap.put(fieldName, errorMessage);
        }
        System.out.println( validationErrorMap);
        System.out.println("\n");
        return validationErrorMap;
    }

}
