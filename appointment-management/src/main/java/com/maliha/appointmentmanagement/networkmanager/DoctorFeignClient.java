package com.maliha.appointmentmanagement.networkmanager;

import com.maliha.appointmentmanagement.model.DoctorFeignDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "doctor-admin-management", configuration = FeignClientConfiguration.class)
public interface DoctorFeignClient {
    @GetMapping("/doctor/getDoctorByEmail")
    DoctorFeignDTO getDoctorByEmail(@RequestParam("email") String email);
}