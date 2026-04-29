package com.ben;

import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Controller
public class BloodDonorController {

    @GetMapping
    public String showDonorForm(Model model) {

        // Put a new Donor object on the page so the form has something to bind to
        model.addAttribute("donor", new Donor());

        // Show the form page
        return "donor_form";
    }

    @PostMapping("/submitDonorForm")
    public String processDonorForm(@Valid Donor donor, BindingResult bindingResult, Model model) {

        // If validation failed, collect the errors and send them back to the form
        if (bindingResult.hasFieldErrors()) {

            // Get all validation errors for the fields
            List<FieldError> fieldErrors = bindingResult.getFieldErrors();

            // Convert errors into a simple map like { "age" -> "Age must be positive" }
            Map<String, String> validationErrorMap = ErrorUtils.createErrorMap(fieldErrors);

            // Add the error messages to the model so the form can display them
            model.addAttribute("validationErrorMap", validationErrorMap);

            return "donor_form";
        }
        else {
            // No validation errors. Now check eligibility rules
            String resultMessage;
            List<String> ineligibleReasons = new ArrayList<>();

            // Check age requirement
            if (donor.getAge() < 16) {
                ineligibleReasons.add("Age must be at least 16");
            }

            // Check weight requirement
            if (donor.getWeight() < 110) {
                ineligibleReasons.add("Weight must be at least 110lbs");
            }

            // If the list is empty, they passed all checks
            if (ineligibleReasons.isEmpty()) {
                resultMessage = "You may be eligible to donate blood";
            } else {
                // Otherwise, they failed at least one requirement
                resultMessage = "You are not eligible to donate blood";

                // Send the list of reasons to the results page
                model.addAttribute("ineligibleReasons", ineligibleReasons);
            }

            // Add the final message to the model
            model.addAttribute("resultMessage", resultMessage);

            // Show the results page
            return "results";
        }
    }
}
