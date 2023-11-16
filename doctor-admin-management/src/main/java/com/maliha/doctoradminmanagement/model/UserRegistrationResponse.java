package com.maliha.doctoradminmanagement.model;


import com.maliha.doctoradminmanagement.entity.DoctorEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserRegistrationResponse {
    private DoctorEntity doctorEntity;
    private String token;

}
