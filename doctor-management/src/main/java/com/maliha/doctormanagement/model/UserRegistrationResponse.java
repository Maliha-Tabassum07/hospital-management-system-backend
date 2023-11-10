package com.maliha.doctormanagement.model;


import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class UserRegistrationResponse {
    private DoctorDTO userDto;
    private String token;

    public UserRegistrationResponse(DoctorDTO userDto, String token) {
        this.userDto = userDto;
        this.token = token;
    }

}
