package com.maliha.doctoradminmanagement.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DoctorViewDTO {
    private String specialId;
    private String name;
    private String imageUrl;
    private String qualification;
    private DepartmentDTO departmentDTO;
    private DesignationDTO designationDTO;
    private List<String> specialtyList;
}
