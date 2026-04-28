package com.ben;

import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class BloodDonorController {

    @GetMapping
    public String showDonorForm(Model model) {
        model.addAttribute("donor", new Donor());
        return "donor_form";
    }

    @PostMapping("/submitDonorForm")
    public String processDonorForm(@Valid Donor donor, Model model) {

        String resultMessage;

        if(donor.getAge() >= 16 && donor.getWeight() >= 110) {
            resultMessage = "You may be eligible to donate blood";
        } else {
            resultMessage = "You are not eligible to donate blood";
        }
        model.addAttribute("resultMessage", resultMessage);
        return "results";
    }

}
