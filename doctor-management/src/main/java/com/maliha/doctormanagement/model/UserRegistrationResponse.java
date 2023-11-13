package com.maliha.doctormanagement.model;


import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class UserRegistrationResponse {
    private DoctorDTO doctorDto;
    private String token;

    public UserRegistrationResponse(DoctorDTO doctorDto, String token) {
        this.doctorDto = doctorDto;
        this.token = token;
    }

}
