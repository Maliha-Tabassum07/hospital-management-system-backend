package com.maliha.doctoradminmanagement.model;
import jakarta.validation.constraints.NotBlank;
public class DoctorLoginDTO {

    @NotBlank(message = "Email cannot be null")
    private String email;
    @NotBlank(message = "Password cannot be null")
    private String password;
    public DoctorLoginDTO() {
    }
    public DoctorLoginDTO(String email, String password) {
        this.email = email;
        this.password = password;
    }
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
