package com.maliha.patientmanagement.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
@Data
@NoArgsConstructor
@AllArgsConstructor
public class RecommendationDTO {
    private List<String> medicine;
    private List<String> doctorName;
    private String treatmentPlan;
    private String recommendation;
}
