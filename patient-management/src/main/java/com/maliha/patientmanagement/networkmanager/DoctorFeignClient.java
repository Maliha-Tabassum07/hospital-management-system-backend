package com.maliha.patientmanagement.networkmanager;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@FeignClient(name = "doctor-admin-management", configuration = FeignClientConfiguration.class)
public interface DoctorFeignClient {
    @GetMapping("/doctor/getAllDoctorByDepartmentId")
    public List<String> getAllDoctorByDepartmentId(@RequestParam Long departmentId);
}