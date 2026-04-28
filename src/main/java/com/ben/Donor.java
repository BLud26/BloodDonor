package com.ben;

import jakarta.validation.constraints.*;

public class Donor {

    @NotNull(message="Name is required")
    @NotBlank(message="Name is required")
    @Size(min=2, max=50, message="Name must be at least 2 characters")
    private String firstName;

    @Min(value = 0, message="Age must be positive number")
            @Max(value = 130, message="Age must be 130 or less")
    int age;


    @Min(value = 5, message="weight must be at least 5")
            @Max(value=600, message="weight must be 600lbs or less")
    int weight;

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    public Donor() {



    }

}
