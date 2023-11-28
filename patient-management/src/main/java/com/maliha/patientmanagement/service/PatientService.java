package com.maliha.patientmanagement.service;

import com.maliha.patientmanagement.entity.HealthRecordEntity;
import com.maliha.patientmanagement.entity.PatientEntity;
import com.maliha.patientmanagement.exception.CustomException;
import com.maliha.patientmanagement.model.*;
import com.maliha.patientmanagement.networkmanager.DoctorFeignClient;
import com.maliha.patientmanagement.networkmanager.MedicineFeignClient;
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
    private DoctorFeignClient doctorFeignClient;
    @Autowired
    private MedicineFeignClient medicineFeignClient;
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
    public PatientEntity updatePatientProfile(PatientDTO patientDTO) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        PatientEntity patientEntity = patientRepository.findByEmail(authentication.getName()).get();
        patientEntity.setEmail(patientDTO.getEmail());
        patientEntity.setPhone(patientDTO.getPhone());
        patientEntity.setMaritalStatus(patientDTO.getMaritalStatus());
        patientEntity.setOccupation(patientDTO.getOccupation());
        patientEntity.setNationality(patientDTO.getNationality());
        patientEntity.setEmergencyContactName(patientDTO.getEmergencyContactName());
        patientEntity.setEmergencyContactNo(patientDTO.getEmergencyContactNo());
        return patientRepository.save(patientEntity);
    }
    public HealthRecordViewDTO updateHealthRecord(HealthRecordDTO healthRecordDTO) throws Exception{
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        PatientEntity patientEntity = patientRepository.findByEmail(authentication.getName()).get();
        Integer patientId=patientEntity.getId();
        if (healthRecordRepository.findById(patientId).isPresent()) {
            HealthRecordEntity healthDataEntity = healthRecordRepository.findById(patientId).get();
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
            healthDataEntity.setAllergy(healthRecordDTO.getAllergy());
            HealthRecordViewDTO healthRecordViewDTO = new ModelMapper().map(healthRecordRepository.save(healthDataEntity), HealthRecordViewDTO.class);
            healthRecordViewDTO.setSpecialId(patientEntity.getSpecialId());
            return healthRecordViewDTO;
        }
        else throw new Exception("Create health Record first");
    }
    public HealthRecordViewDTO createHealthRecord(HealthRecordDTO healthRecordDTO) throws Exception{
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Optional<PatientEntity> patientEntity = patientRepository.findByEmail(authentication.getName());
        Integer id = patientEntity.orElseThrow(() -> new Exception()).getId();
        if (healthRecordRepository.findById(id).isPresent()) {
            throw new Exception("This user already exists in the database");
        }
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
        healthDataEntity.setAllergy(healthRecordDTO.getAllergy());
        HealthRecordViewDTO healthRecordViewDTO= new ModelMapper().map(healthRecordRepository.save(healthDataEntity), HealthRecordViewDTO.class);
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
    public List<HealthRecordViewDTO> getAllHealthRecord(){
        List<HealthRecordViewDTO> healthRecordDTOList=new ArrayList<>();
        HealthRecordViewDTO healthRecordViewDTO=new HealthRecordViewDTO();
        for(HealthRecordEntity healthRecordEntity:healthRecordRepository.findAll()){
            healthRecordViewDTO=new ModelMapper().map(healthRecordEntity,HealthRecordViewDTO.class);
            healthRecordViewDTO.setSpecialId(patientRepository.findById(healthRecordEntity.getId()).get().getSpecialId());
            healthRecordDTOList.add(healthRecordViewDTO);
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
        throw new CustomException("Patient doesn't exist");
    }
    public PatientViewDTO getSelfInfo()throws Exception{
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Optional<PatientEntity> patientEntity = patientRepository.findByEmail(authentication.getName());
        Integer id = patientEntity.orElseThrow(() -> new Exception()).getId();
        if(patientRepository.existsById(id)){
            return new ModelMapper().map(patientRepository.findById(id).get(),PatientViewDTO.class);
        }
        throw new Exception("Patient doesn't exist");
    }
    public long getCount() {
        return patientRepository.count();
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
    public RecommendationDTO getRecommendation()throws Exception{
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Optional<PatientEntity> patientEntity = patientRepository.findByEmail(authentication.getName());
        Integer id = patientEntity.orElseThrow(() -> new CustomException("Patient Not found")).getId();
        RecommendationDTO recommendationDTO=new RecommendationDTO();
        HealthRecordEntity healthRecordEntity=healthRecordRepository.findById(id).orElseThrow(() -> new Exception());
        if(healthRecordEntity.getBloodPressure().equals("HIGH")){
            recommendationDTO.setDoctorName(doctorFeignClient.getAllDoctorByDepartmentId(1l));
            List<String> recommendationList=new ArrayList<>();
            recommendationList.add(" < Your blood pressure is high. Avoid fatty food. > ");
            recommendationDTO.setRecommendation(recommendationList);
            recommendationDTO.setMedicine(medicineFeignClient.getAllMedicineBySymptomId(4l));
            recommendationDTO.getMedicine().add(", ");
            List<String> treatmentPlan=new ArrayList<>();
            treatmentPlan.add(" < You need treatment for high blood pressure > ");
            recommendationDTO.setTreatmentPlan(treatmentPlan);
            if (healthRecordEntity.getBloodSugarLevel()>=7.5){
                for (String doctorName:doctorFeignClient.getAllDoctorByDepartmentId(5l)){
                    recommendationDTO.getDoctorName().add(doctorName);
                }
                recommendationList.add(" < Your blood sugar level is high. Avoid sugary food and consume less carbohydrate > ");
                recommendationDTO.setRecommendation(recommendationList);
                for (String medicineName:medicineFeignClient.getAllMedicineBySymptomId(5l)){
                    recommendationDTO.getMedicine().add(medicineName+",  ");
                }
                treatmentPlan.add("< You need checkup for diabetes >");
                recommendationDTO.setTreatmentPlan(treatmentPlan);
                if (healthRecordEntity.getAllergy()){
                    for (String doctorName:doctorFeignClient.getAllDoctorByDepartmentId(4l)){
                        recommendationDTO.getDoctorName().add(doctorName);
                    }
                    recommendationList.add("< As you have allergy avoid foods like prawn, eggplant or red meats >" );
                    recommendationDTO.setRecommendation(recommendationList);
                    for (String medicineName:medicineFeignClient.getAllMedicineBySymptomId(7l)){
                        recommendationDTO.getMedicine().add(medicineName+",  ");
                    }
                    treatmentPlan.add("< You can visit our dermatologists for more detailed consultation >");
                    recommendationDTO.setTreatmentPlan(treatmentPlan);
                }
            }else {
                if (healthRecordEntity.getAllergy()){
                    for (String doctorName:doctorFeignClient.getAllDoctorByDepartmentId(4l)){
                        recommendationDTO.getDoctorName().add(doctorName);
                    }
                    recommendationList.add("< As you have allergy avoid foods like prawn, eggplant or red meats >" );
                    recommendationDTO.setRecommendation(recommendationList);
                    for (String medicineName:medicineFeignClient.getAllMedicineBySymptomId(7l)){
                        recommendationDTO.getMedicine().add(medicineName+ ",  ");
                    }
                    treatmentPlan.add("< You can visit our dermatologists for more detailed consultation >");
                    recommendationDTO.setTreatmentPlan(treatmentPlan);
                }
            }
        }
        else {
            if (healthRecordEntity.getBloodSugarLevel()>=7.5){
                recommendationDTO.setDoctorName(doctorFeignClient.getAllDoctorByDepartmentId(5l));
                List<String> recommendationList=new ArrayList<>();
                recommendationList.add("< Your blood sugar level is high. Avoid sugary food and consume less carbohydrate >");
                recommendationDTO.setRecommendation(recommendationList);
                recommendationDTO.setMedicine(medicineFeignClient.getAllMedicineBySymptomId(5l));
                recommendationDTO.getMedicine().add(", ");
                List<String> treatmentPlan=new ArrayList<>();
                treatmentPlan.add("< You need checkup for diabetes >");
                recommendationDTO.setTreatmentPlan(treatmentPlan);
                if (healthRecordEntity.getAllergy()){
                    for (String doctorName:doctorFeignClient.getAllDoctorByDepartmentId(4l)){
                        recommendationDTO.getDoctorName().add(doctorName);
                    }
                    recommendationList.add("< As you have allergy avoid foods like prawn, eggplant or red meats >" );
                    recommendationDTO.setRecommendation(recommendationList);
                    for (String medicineName:medicineFeignClient.getAllMedicineBySymptomId(7l)){
                        recommendationDTO.getMedicine().add(medicineName+ ", ");
                    }
                    treatmentPlan.add("< You can visit our dermatologists for more detailed consultation >");
                    recommendationDTO.setTreatmentPlan(treatmentPlan);
                }
            }else {
                if (healthRecordEntity.getAllergy()){
                    recommendationDTO.setDoctorName(doctorFeignClient.getAllDoctorByDepartmentId(4l));
                    List<String> recommendationList=new ArrayList<>();
                    recommendationList.add("< As you have allergy avoid foods like prawn, eggplant or red meats >");
                    recommendationDTO.setRecommendation(recommendationList);
                    recommendationDTO.setMedicine(medicineFeignClient.getAllMedicineBySymptomId(7l));
                    List<String> treatmentPlan=new ArrayList<>();
                    treatmentPlan.add("< You can visit our dermatologists for more detailed consultation > ");
                    recommendationDTO.setTreatmentPlan(treatmentPlan);
                }
                else {
                    List<String> recommendationList=new ArrayList<>();
                    recommendationList.add(" This recommendation only work for diabetes, high blood pressure and allergy.Fortunately, you don't have those. For other diseases visit our consultants. Thank you!");
                    recommendationDTO.setRecommendation(recommendationList);
                }
            }
        }
        return recommendationDTO;
    }

}

