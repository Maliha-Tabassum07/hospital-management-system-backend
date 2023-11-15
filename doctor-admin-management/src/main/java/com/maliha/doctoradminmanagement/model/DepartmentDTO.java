package com.maliha.doctoradminmanagement.model;

public class DepartmentDTO {
    private String name;
    private String description;
    private Integer floorNo;
    public DepartmentDTO() {
    }
    public DepartmentDTO(String name, String description, Integer floorNo) {
        this.name = name;
        this.description = description;
        this.floorNo= floorNo;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getFloorNo() {
        return floorNo;
    }

    public void setFloorNo(Integer floorNo) {
        this.floorNo = floorNo;
    }
}
