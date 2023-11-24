package com.maliha.appointmentmanagement.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PatientFeignDTO {
    private Integer id;
    //@Email(message = "Invalid email")
    private String email;
    //@NotNull(message = "Password is required")
    private String password;
    // @NotNull(message = "Role is required")
    private String role;
    private String name;
    private String specialId;
}
