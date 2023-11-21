package com.maliha.doctoradminmanagement.model;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DoctorDTO {
    private String specialId;
    private Integer id;
    private String name;
    @NotBlank(message = "Your Id cannot be null")
    @Email(message = "Email must be a valid email address")
    private String email;
    @NotBlank(message = "Password cannot be null")
    private String password;
    private String address;
    private String imageUrl;
    private String qualification;
    private String role;
    private Long department;
    private Long designation;
    private Integer room;
    private String phone;
    private List<Long> specialty;
}
