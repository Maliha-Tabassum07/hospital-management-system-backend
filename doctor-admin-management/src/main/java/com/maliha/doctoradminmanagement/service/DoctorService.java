package com.maliha.doctoradminmanagement.service;


import com.maliha.doctoradminmanagement.entity.DepartmentEntity;
import com.maliha.doctoradminmanagement.entity.DoctorEntity;
import com.maliha.doctoradminmanagement.model.*;
import com.maliha.doctoradminmanagement.repository.DepartmentRepository;
import com.maliha.doctoradminmanagement.repository.DesignationRepository;
import com.maliha.doctoradminmanagement.repository.DoctorRepository;
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

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class DoctorService implements UserDetailsService {
    @Autowired
    private DoctorRepository doctorRepository;
    @Autowired
    private DepartmentRepository departmentRepository;
    @Autowired
    private DesignationRepository designationRepository;
    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;
    public DoctorEntity createUser(DoctorDTO doctorDTO) throws Exception {
        if(doctorRepository.findByEmail(doctorDTO.getEmail()).isPresent())
            throw new Exception("Record already exists");
        DoctorEntity userEntity = new DoctorEntity();
        userEntity.setName(doctorDTO.getName());
        userEntity.setEmail(doctorDTO.getEmail());
        userEntity.setPassword(bCryptPasswordEncoder.encode(doctorDTO.getPassword()));
        userEntity.setRole("DOCTOR");
        userEntity.setAddress(doctorDTO.getAddress());
        userEntity.setImageUrl(doctorDTO.getImageUrl());
        userEntity.setPhone(doctorDTO.getPhone());
        userEntity.setQualification(doctorDTO.getQualification());
        userEntity.setDepartment(departmentRepository.findById(doctorDTO.getDepartment()).orElseThrow(() -> new Exception()));
        userEntity.setRoom(doctorDTO.getRoom());
        userEntity.setDesignation(designationRepository.findById(doctorDTO.getDesignation()).orElseThrow(() -> new Exception()));
        List<DepartmentEntity> departmentEntityList=new ArrayList<>();
        for(Long id:doctorDTO.getSpecialty()){
            departmentEntityList.add(departmentRepository.findById(id).orElseThrow(() -> new Exception()));
        }
        userEntity.setSpecialty(departmentEntityList);
        DoctorEntity storedUserDetails =doctorRepository.save(userEntity);
        storedUserDetails.setSpecialId(String.format("%s%02d", "D", storedUserDetails.getId()));
        return doctorRepository.save(storedUserDetails);
    }
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
    public List<DoctorViewDTO> getAllDoctor(){
        List<DoctorEntity> doctorEntityList = doctorRepository.findAllByRole("DOCTOR");
        List<DoctorViewDTO> doctorViewDTOList=new ArrayList<>();
        for(DoctorEntity doctorEntity:doctorEntityList){
            DoctorViewDTO doctorViewDTO = new ModelMapper().map(doctorEntity, DoctorViewDTO.class);
            List<String> specialtyList=new ArrayList<>();
            for (DepartmentEntity departmentEntity:doctorEntity.getSpecialty()){
                specialtyList.add(departmentEntity.getName());
            }
            doctorViewDTO.setDepartmentDTO(new ModelMapper().map(doctorEntity.getDepartment(), DepartmentDTO.class));
            doctorViewDTO.setDesignationDTO(new ModelMapper().map(doctorEntity.getDesignation(), DesignationDTO.class));
            doctorViewDTO.setSpecialtyList(specialtyList);
            doctorViewDTOList.add(doctorViewDTO);
        }
        return doctorViewDTOList;
    }
    public DoctorEntity getDoctorById() throws Exception{
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Optional<DoctorEntity> doctorEntity = doctorRepository.findByEmail(authentication.getName());
        Integer id = doctorEntity.orElseThrow(() -> new Exception()).getId();
        return doctorRepository.findById(id).orElseThrow(() -> new Exception());
    }
    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        DoctorEntity userEntity = doctorRepository.findByEmail(email).get();
        List<GrantedAuthority> roles = new ArrayList<>();
        roles.add(new SimpleGrantedAuthority(userEntity.getRole()));
        if (userEntity == null) throw new UsernameNotFoundException(email);
        return new User(userEntity.getEmail(), userEntity.getPassword(),
                true, true, true, true,
                roles);
    }
    public DoctorEntity getDoctorBySpecialId(String id) throws Exception{
        return doctorRepository.findBySpecialId(id).orElseThrow(() -> new Exception());
    }
    public DoctorEntity getDoctorByName(String name) throws Exception{
        return doctorRepository.findByName(name).orElseThrow(() -> new Exception());
    }
    public DoctorEntity updateDoctor(DoctorDTO doctorDTO) throws Exception{
        Integer id = doctorRepository.findByEmail(SecurityContextHolder.getContext().getAuthentication().getName()).orElseThrow(() -> new NullPointerException()).getId();
        DoctorEntity doctorEntity=doctorRepository.findById(id).orElseThrow(() -> new NullPointerException());
        doctorEntity.setName(doctorDTO.getName());
        doctorEntity.setAddress(doctorDTO.getAddress());
        doctorEntity.setQualification(doctorDTO.getQualification());
        doctorEntity.setPhone(doctorDTO.getPhone());
        doctorEntity.setDepartment(departmentRepository.findById(doctorDTO.getDepartment()).orElseThrow(() -> new Exception()));
        doctorEntity.setDesignation(designationRepository.findById(doctorDTO.getDesignation()).orElseThrow(() -> new Exception()));
        List<DepartmentEntity> departmentEntityList=new ArrayList<>();
        for(Long departmentId:doctorDTO.getSpecialty()){
            departmentEntityList.add(departmentRepository.findById(departmentId).orElseThrow(() -> new Exception()));
        }
        doctorEntity.setSpecialty(departmentEntityList);
        return doctorRepository.save(doctorEntity);
    }
    public List<DoctorViewDTO> getAllDoctorbyDepartmentId(Long departmentId){
        List<DoctorEntity> doctorEntityList = doctorRepository.findAllByDepartmentId(departmentId);
        List<DoctorViewDTO> doctorViewDTOList=new ArrayList<>();
        for(DoctorEntity doctorEntity:doctorEntityList){
            DoctorViewDTO doctorViewDTO = new ModelMapper().map(doctorEntity, DoctorViewDTO.class);
            List<String> specialtyList=new ArrayList<>();
            for (DepartmentEntity departmentEntity:doctorEntity.getSpecialty()){
                specialtyList.add(departmentEntity.getName());
            }
            doctorViewDTO.setDepartmentDTO(new ModelMapper().map(doctorEntity.getDepartment(), DepartmentDTO.class));
            doctorViewDTO.setDesignationDTO(new ModelMapper().map(doctorEntity.getDesignation(), DesignationDTO.class));
            doctorViewDTO.setSpecialtyList(specialtyList);
            doctorViewDTOList.add(doctorViewDTO);
        }
        return doctorViewDTOList.stream()
                .sorted(Comparator.comparing(DoctorViewDTO::getName))
                .collect(Collectors.toList());
    }
    public List<DoctorViewDTO> getAllDoctorByDesignationId(Long designationId){
        List<DoctorEntity> doctorEntityList = doctorRepository.findAllByDesignationId(designationId);
        List<DoctorViewDTO> doctorViewDTOList=new ArrayList<>();
        for(DoctorEntity doctorEntity:doctorEntityList){
            DoctorViewDTO doctorViewDTO = new ModelMapper().map(doctorEntity, DoctorViewDTO.class);
            List<String> specialtyList=new ArrayList<>();
            for (DepartmentEntity departmentEntity:doctorEntity.getSpecialty()){
                specialtyList.add(departmentEntity.getName());
            }
            doctorViewDTO.setDepartmentDTO(new ModelMapper().map(doctorEntity.getDepartment(), DepartmentDTO.class));
            doctorViewDTO.setDesignationDTO(new ModelMapper().map(doctorEntity.getDesignation(), DesignationDTO.class));
            doctorViewDTO.setSpecialtyList(specialtyList);
            doctorViewDTOList.add(doctorViewDTO);
        }
        return doctorViewDTOList.stream()
                .sorted(Comparator.comparing(DoctorViewDTO::getName))
                .collect(Collectors.toList());
    }
    public DoctorFeignDTO getDoctorByEmail(String email) {
        DoctorEntity doctorEntity = doctorRepository.findByEmail(email).get();
        System.out.println(email);
        return  new ModelMapper().map(doctorEntity, DoctorFeignDTO.class);
    }
    public List<String> getAllDoctorNameByDepartmentId(Long departmentId){
        List<String> doctorName=new ArrayList<>();
        List<DoctorViewDTO> doctorViewDTOList=getAllDoctorbyDepartmentId(departmentId);
        for (DoctorViewDTO doctorViewDTO:doctorViewDTOList){
            doctorName.add(doctorViewDTO.getName());
        }
        return doctorName;
    }
    public List<DoctorViewDTO> getAllDoctorByDepartmentName(String departmentName)throws Exception{
        if(departmentRepository.existsByName(departmentName)){
            return getAllDoctorbyDepartmentId(departmentRepository.findByName(departmentName).get().getId());
        }
        throw new Exception();
    }
    public List<DoctorViewDTO> getAllDoctorByDesignationName(String designationName)throws Exception{
        if(designationRepository.existsByName(designationName)){
            return getAllDoctorByDesignationId(designationRepository.findByName(designationName).get().getId());
        }
        throw new Exception();
    }
    public List<DoctorViewDTO> doctorHelpDesk(String searchInput) throws Exception{
        if(departmentRepository.existsByName(searchInput)){
            return getAllDoctorByDepartmentName(searchInput);
        }
        else if (designationRepository.existsByName(searchInput)){
            return getAllDoctorByDesignationName(searchInput);
        }
        else if (doctorRepository.existsByName(searchInput)){
            List<DoctorViewDTO> doctorViewDTOList=new ArrayList<>();
            for(DoctorEntity doctorEntity:doctorRepository.findAllByName(searchInput)){
                DoctorViewDTO doctorViewDTO = new ModelMapper().map(doctorEntity, DoctorViewDTO.class);
                List<String> specialtyList=new ArrayList<>();
                for (DepartmentEntity departmentEntity:doctorEntity.getSpecialty()){
                    specialtyList.add(departmentEntity.getName());
                }
                doctorViewDTO.setDepartmentDTO(new ModelMapper().map(doctorEntity.getDepartment(), DepartmentDTO.class));
                doctorViewDTO.setDesignationDTO(new ModelMapper().map(doctorEntity.getDesignation(), DesignationDTO.class));
                doctorViewDTO.setSpecialtyList(specialtyList);
                doctorViewDTOList.add(doctorViewDTO);
            }
            return doctorViewDTOList;
        }
        else if(doctorRepository.existsBySpecialId(searchInput)){
            List<DoctorViewDTO> doctorViewDTOList=new ArrayList<>();
            for(DoctorEntity doctorEntity:doctorRepository.findAllBySpecialId(searchInput)){
                DoctorViewDTO doctorViewDTO = new ModelMapper().map(doctorEntity, DoctorViewDTO.class);
                List<String> specialtyList=new ArrayList<>();
                for (DepartmentEntity departmentEntity:doctorEntity.getSpecialty()){
                    specialtyList.add(departmentEntity.getName());
                }
                doctorViewDTO.setDepartmentDTO(new ModelMapper().map(doctorEntity.getDepartment(), DepartmentDTO.class));
                doctorViewDTO.setDesignationDTO(new ModelMapper().map(doctorEntity.getDesignation(), DesignationDTO.class));
                doctorViewDTO.setSpecialtyList(specialtyList);
                doctorViewDTOList.add(doctorViewDTO);
            }
            return doctorViewDTOList;
        }
        throw new Exception("Wrong Input");
    }

}
