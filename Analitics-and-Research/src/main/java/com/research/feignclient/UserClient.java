package com.research.feignclient;


import com.research.dto.HealthRecordViewDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.cloud.openfeign.FeignClientProperties;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@FeignClient(name = "patient-management", configuration = FeignClientProperties.FeignClientConfiguration.class)
public interface UserClient {
    @GetMapping("/patient/all/health-record")
    public List<HealthRecordViewDTO> getAllPatientHealthRecord();

}