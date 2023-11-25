package com.maliha.communitymanagement.networkmanager;


import com.maliha.communitymanagement.model.PatientFeignDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "patient-management", configuration = FeignClientConfiguration.class)
public interface PatientFeignClient {
    @GetMapping("patient/getPatientByEmail")
    public PatientFeignDTO getPatientByEmail(@RequestParam("email") String email);
}
