package com.maliha.doctormanagement.service;

import com.maliha.doctormanagement.entity.DoctorEntity;
import com.maliha.doctormanagement.model.DoctorDTO;
import com.maliha.doctormanagement.repository.DoctorRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class DoctorService {
    @Autowired
    private DoctorRepository doctorRepository;
    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;
    public DoctorEntity readByEmail(String email) {
        return doctorRepository.findByEmail(email).get();
    }
    public DoctorDTO getDoctor(String email) {
        DoctorEntity doctorEntity = doctorRepository.findByEmail(email).get();
        if(doctorEntity == null) throw new UsernameNotFoundException("No record found");
        DoctorDTO returnValue = new DoctorDTO();
        BeanUtils.copyProperties(doctorEntity,returnValue);
        return returnValue;
    }
}
