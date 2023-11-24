package com.maliha.patientmanagement.service;

import com.maliha.patientmanagement.entity.HealthRecordEntity;
import com.maliha.patientmanagement.entity.PatientEntity;
import com.maliha.patientmanagement.model.*;
import com.maliha.patientmanagement.repository.HealthRecordRepository;
import com.maliha.patientmanagement.repository.PatientRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class PatientService implements UserDetailsService {
    @Autowired
    private PatientRepository patientRepository;
    @Autowired
    private HealthRecordRepository healthRecordRepository;
    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

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
        patientEntity.setGender(patientDTO.getGender());
        patientEntity.setFatherName(patientDTO.getFatherName());
        patientEntity.setMotherName(patientDTO.getMotherName());
        patientEntity.setMaritalStatus(patientDTO.getMaritalStatus());
        patientEntity.setOccupation(patientDTO.getOccupation());
        patientEntity.setNationality(patientDTO.getNationality());
        patientEntity.setEmergencyContactName(patientDTO.getEmergencyContactName());
        patientEntity.setEmergencyContactNo(patientDTO.getEmergencyContactNo());
        PatientEntity storedPatientDetails = patientRepository.save(patientEntity);
        storedPatientDetails.setSpecialId(String.format("%s%02d", "P", storedPatientDetails.getId()));
        return patientRepository.save(storedPatientDetails);
    }
    public HealthRecordViewDTO createHealthRecord(HealthRecordDTO healthRecordDTO) throws Exception{
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Optional<PatientEntity> patientEntity = patientRepository.findByEmail(authentication.getName());
        Integer id = patientEntity.orElseThrow(() -> new Exception()).getId();
        if (healthRecordRepository.findById(id).isPresent()) {
            throw new Exception("This user already exists in the database");
        }
        HealthRecordViewDTO healthRecordViewDTO=new HealthRecordViewDTO();
        HealthRecordEntity healthDataEntity = new HealthRecordEntity();
        healthDataEntity.setId(id);
        healthDataEntity.setHeightCm(healthRecordDTO.getHeightCm());
        healthDataEntity.setWeightKg(healthRecordDTO.getWeightKg());
        healthDataEntity.setBloodSugarLevel(healthRecordDTO.getBloodSugarLevel());
        healthDataEntity.setHeartRate(healthRecordDTO.getHeartRate());
        healthDataEntity.setBmi(healthRecordDTO.getWeightKg() / ((healthRecordDTO.getHeightCm() / 100) * (healthRecordDTO.getHeightCm() / 100)));
        healthDataEntity.setDate(LocalDate.now());
        healthDataEntity.setSleepHour(healthRecordDTO.getSleepHour());
        healthDataEntity.setBloodGroup(healthRecordDTO.getBloodGroup());
        healthDataEntity.setBloodPressure(healthRecordDTO.getBloodPressure());
        healthDataEntity.setSmoke(healthRecordDTO.getSmoke());
        healthRecordViewDTO= new ModelMapper().map(healthRecordRepository.save(healthDataEntity), HealthRecordViewDTO.class);
        healthRecordViewDTO.setSpecialId(patientEntity.get().getSpecialId());
        return healthRecordViewDTO;
    }
    public List<PatientViewDTO> getAllPatient(){
        List<PatientViewDTO> patientViewDTOList=new ArrayList<>();
        for(PatientEntity patientEntity:patientRepository.findAll()){
            patientViewDTOList.add(new ModelMapper().map(patientEntity,PatientViewDTO.class));
        }
      return patientViewDTOList;
    }
    public List<HealthRecordDTO> getAllHealthRecord(){
        List<HealthRecordDTO> healthRecordDTOList=new ArrayList<>();
        for(HealthRecordEntity healthRecordEntity:healthRecordRepository.findAll()){
            healthRecordDTOList.add(new ModelMapper().map(healthRecordEntity,HealthRecordDTO.class));
        }
        return healthRecordDTOList;
    }
    public HealthRecordViewDTO getHealthRecordById(Integer id)throws Exception{
        HealthRecordViewDTO healthRecordViewDTO =new HealthRecordViewDTO();
        if(healthRecordRepository.existsById(id)){
            healthRecordViewDTO=new ModelMapper().map(healthRecordRepository.findById(id).get(),HealthRecordViewDTO.class);
            healthRecordViewDTO.setSpecialId(patientRepository.findById(id).get().getSpecialId());
            return healthRecordViewDTO;
        }
        throw new Exception("Health Record doesn't exist");
    }
    public PatientViewDTO getPatientById(Integer id)throws Exception{
        PatientViewDTO patientViewDTO =new PatientViewDTO();
        if(patientRepository.existsById(id)){
            return new ModelMapper().map(patientRepository.findById(id).get(),PatientViewDTO.class);
        }
        throw new Exception("Patient doesn't exist");
    }
    public PatientViewDTO getSelfInfo()throws Exception{
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Optional<PatientEntity> patientEntity = patientRepository.findByEmail(authentication.getName());
        Integer id = patientEntity.orElseThrow(() -> new Exception()).getId();
        PatientViewDTO patientViewDTO =new PatientViewDTO();
        if(patientRepository.existsById(id)){
            return new ModelMapper().map(patientRepository.findById(id).get(),PatientViewDTO.class);
        }
        throw new Exception("Patient doesn't exist");
    }
    public HealthRecordViewDTO getSelfHealthRecord()throws Exception{
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Optional<PatientEntity> patientEntity = patientRepository.findByEmail(authentication.getName());
        Integer id = patientEntity.orElseThrow(() -> new Exception()).getId();
        HealthRecordViewDTO healthRecordViewDTO =new HealthRecordViewDTO();
        if(healthRecordRepository.existsById(id)){
            healthRecordViewDTO=new ModelMapper().map(healthRecordRepository.findById(id).get(),HealthRecordViewDTO.class);
            healthRecordViewDTO.setSpecialId(patientRepository.findById(id).get().getSpecialId());
            return healthRecordViewDTO;
        }
        throw new Exception("Health Record doesn't exist");
    }
    public PatientFeignDTO getPatientByEmail(String email) {
        PatientEntity patientEntity = patientRepository.findByEmail(email).get();
        return  new ModelMapper().map(patientEntity, PatientFeignDTO.class);
    }
}

