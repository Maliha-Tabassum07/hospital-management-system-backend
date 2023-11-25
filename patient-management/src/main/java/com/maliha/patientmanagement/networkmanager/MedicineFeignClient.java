package com.maliha.patientmanagement.networkmanager;

import com.maliha.patientmanagement.model.MedicineDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@FeignClient(name = "pharmacy-management", configuration = FeignClientConfiguration.class)
public interface MedicineFeignClient {
    @GetMapping("/pharmacy/medicineBySymptomId")
    public List<String> getAllMedicineBySymptomId(@RequestParam Long symptomId);
}
