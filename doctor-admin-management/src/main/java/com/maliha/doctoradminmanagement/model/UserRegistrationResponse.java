package com.maliha.doctoradminmanagement.model;


import com.maliha.doctoradminmanagement.entity.DoctorEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserRegistrationResponse {
    private DoctorEntity doctorDto;
    private String token;

//    public UserRegistrationResponse(DoctorDTO doctorDto, String token) {
//        this.doctorDto = doctorDto;
//        this.token = token;
//    }

}
