package com.example.tinyshop.model.dtos;

import com.example.tinyshop.model.enums.GenderCategory;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.*;
import java.time.LocalDate;

public class UserRegistrationDTO {

    @Size(min = 4, max = 25)
    @NotBlank
    private String fullName;

    @NotNull
    @Past
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate birthDate;

    @Size(min = 4,max = 30)
    @Email
    private String email;

    @NotNull
    private GenderCategory gender;

    @Size(min = 6,max = 20)
    @NotBlank
    private String password;

    @Size(min = 6,max = 20)
    @NotBlank
    private String confirmPassword;

    public UserRegistrationDTO() {

    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public LocalDate getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(LocalDate birthDate) {
        this.birthDate = birthDate;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public GenderCategory getGender() {
        return gender;
    }

    public void setGender(GenderCategory gender) {
        this.gender = gender;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getConfirmPassword() {
        return confirmPassword;
    }

    public void setConfirmPassword(String confirmPassword) {
        this.confirmPassword = confirmPassword;
    }
    public boolean passwordsMatch(){
        return this.getPassword().equals(this.getConfirmPassword());
    }

}
