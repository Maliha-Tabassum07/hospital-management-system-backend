package com.maliha.patientmanagement.service;

import com.maliha.patientmanagement.entity.PatientEntity;
import com.maliha.patientmanagement.model.PatientDTO;
import com.maliha.patientmanagement.repository.PatientRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class PatientService implements UserDetailsService {
    @Autowired
    private PatientRepository patientRepository;

    public PatientEntity readByEmail(String email) {
        return patientRepository.findByEmail(email).get();
    }
    public PatientDTO getPatient(String email) {
        PatientEntity patientEntity = patientRepository.findByEmail(email).get();
        if(patientEntity == null) throw new UsernameNotFoundException("No record found");
        PatientDTO returnValue = new PatientDTO();
        BeanUtils.copyProperties(patientEntity,returnValue);
        return returnValue;
    }
    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        PatientEntity userEntity = patientRepository.findByEmail(email).get();
        List<GrantedAuthority> roles = new ArrayList<>();
        roles.add(new SimpleGrantedAuthority(userEntity.getRole()));
        if (userEntity == null) throw new UsernameNotFoundException(email);
        return new User(userEntity.getEmail(), userEntity.getPassword(),
                true, true, true, true,
                roles);
    }
    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    public PatientEntity createPatient(PatientDTO patientDTO) throws Exception {
        if (patientRepository.findByEmail(patientDTO.getEmail()).isPresent()) {
            throw new Exception("Record already exists");
        }

        PatientEntity patientEntity = new PatientEntity();
        patientEntity.setName(patientDTO.getName());
        patientEntity.setEmail(patientDTO.getEmail());
        patientEntity.setPassword(bCryptPasswordEncoder.encode(patientDTO.getPassword()));
        patientEntity.setRole("PATIENT");
        patientEntity.setAddress(patientDTO.getAddress());
        patientEntity.setImageUrl(patientDTO.getImageUrl());
        patientEntity.setPhone(patientDTO.getPhone());
        // Add additional properties from PatientDTO to PatientEntity
        patientEntity.setGender(patientDTO.getGender());
        patientEntity.setFatherName(patientDTO.getFatherName());
        patientEntity.setMotherName(patientDTO.getMotherName());
        patientEntity.setMaritialStatus(patientDTO.getMaritialStatus());
        patientEntity.setOccupation(patientDTO.getOccupation());
        patientEntity.setNationality(patientDTO.getNationality());
        patientEntity.setEmergencyContactName(patientDTO.getEmergencyContactName());
        patientEntity.setEmergencyContactNo(patientDTO.getEmergencyContactNo());

        PatientEntity storedPatientDetails = patientRepository.save(patientEntity);
        storedPatientDetails.setSpecialId(String.format("%s%02d", "P", storedPatientDetails.getId()));
        return patientRepository.save(storedPatientDetails);
    }
}

