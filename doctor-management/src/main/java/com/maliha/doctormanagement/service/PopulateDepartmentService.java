package com.maliha.doctormanagement.service;

import com.maliha.doctormanagement.entity.DepartmentEntity;
import com.maliha.doctormanagement.repository.DepartmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PopulateDepartmentService {

    @Autowired
    private DepartmentRepository departmentRepository;

    public Boolean populateDepartment(){
        if (departmentRepository.existsById(1l)){
            return false;
        }
        System.out.println("Service");
        DepartmentEntity departmentEntity=new DepartmentEntity();
        departmentEntity.setName("CARDIOLOGY");
        departmentEntity.setDescription("With the help of State-of-the-heart-technology, equipment and monitoring devices, our cardiologists are providing excellent heart care to all the patients.");
        departmentEntity.setFloorNo(3);
        departmentRepository.save(departmentEntity);

        departmentEntity=new DepartmentEntity();
        departmentEntity.setName("COVID");
        departmentEntity.setDescription(" The most robust and comprehensive COVID care facility in the country.");
        departmentEntity.setFloorNo(2);
        departmentRepository.save(departmentEntity);

        departmentEntity=new DepartmentEntity();
        departmentEntity.setName("DENTAL");
        departmentEntity.setDescription("The Dental Centre has been set up with the aim to provide an international standard dental treatment facility to satisfy and meet the demands of our community.");
        departmentEntity.setFloorNo(4);
        departmentRepository.save(departmentEntity);

        departmentEntity=new DepartmentEntity();
        departmentEntity.setName("DERMATOLOGY");
        departmentEntity.setDescription("The Department aims to deliver the highest quality of care to patients with skin disorders, including skin cancers and allergic processes and venereal diseases.");
        departmentEntity.setFloorNo(4);
        departmentRepository.save(departmentEntity);

        departmentEntity=new DepartmentEntity();
        departmentEntity.setName("DIABETES");
        departmentEntity.setDescription("This department provides finest Diabetes Treatment and Management");
        departmentEntity.setFloorNo(3);
        departmentRepository.save(departmentEntity);

        departmentEntity=new DepartmentEntity();
        departmentEntity.setName("ENDOCRINOLOGY");
        departmentEntity.setDescription("This department provides state of the art medical care to individuals with endocrine disorders.");
        departmentEntity.setFloorNo(3);
        departmentRepository.save(departmentEntity);

        departmentEntity=new DepartmentEntity();
        departmentEntity.setName("MEDICINE");
        departmentEntity.setDescription("Medicine doctors can assume responsibility for the total health care needs of the individual");
        departmentEntity.setFloorNo(4);
        departmentRepository.save(departmentEntity);

        departmentEntity=new DepartmentEntity();
        departmentEntity.setName("ENT & HEAD NECK");
        departmentEntity.setDescription("Department of ENT & Head Neck Surgery consists of experienced Surgeons including Anesthetists who are available to provide you the best possible service in Bangladesh.");
        departmentEntity.setFloorNo(1);
        departmentRepository.save(departmentEntity);

        departmentEntity=new DepartmentEntity();
        departmentEntity.setName("GASTRO LIVER");
        departmentEntity.setDescription("The Department of Gastroenterology & Hepatology is devoted to the clinical care of patients with gastrointestinal and liver disorders.");
        departmentEntity.setFloorNo(2);
        departmentRepository.save(departmentEntity);

        departmentEntity=new DepartmentEntity();
        departmentEntity.setName("SURGERY");
        departmentEntity.setDescription("The Department offers a wide range of comprehensive and high quality surgical procedures.");
        departmentEntity.setFloorNo(5);
        departmentRepository.save(departmentEntity);

        departmentEntity=new DepartmentEntity();
        departmentEntity.setName("NEPHROLOGY");
        departmentEntity.setDescription("Advanced treatments for kidney diseases with the best diagnosis of nephrology/kidney care in Dhaka.");
        departmentEntity.setFloorNo(3);
        departmentRepository.save(departmentEntity);

        departmentEntity=new DepartmentEntity();
        departmentEntity.setName("NEUROLOGY");
        departmentEntity.setDescription("Specialization in the evaluation & treatment of disorders affecting the Nervous System");
        departmentEntity.setFloorNo(2);
        departmentRepository.save(departmentEntity);

        departmentEntity=new DepartmentEntity();
        departmentEntity.setName("GYNAECOLOGY");
        departmentEntity.setDescription("Best Gynecologists in Dhaka, Bangladesh");
        departmentEntity.setFloorNo(5);
        departmentRepository.save(departmentEntity);

        departmentEntity=new DepartmentEntity();
        departmentEntity.setName("ONCOLOGY");
        departmentEntity.setDescription("Best Cancer Hospital in Dhaka, Bangladesh consists of one of the best oncologists in Dhaka, Bangladesh");
        departmentEntity.setFloorNo(6);
        departmentRepository.save(departmentEntity);

        departmentEntity=new DepartmentEntity();
        departmentEntity.setName("PAEDIATRICS");
        departmentEntity.setDescription("Pediatrics (also spelled Paediatrics) is a branch of medicine that deals with the medical care of infants, children and adolescents");
        departmentEntity.setFloorNo(7);
        departmentRepository.save(departmentEntity);

        departmentEntity=new DepartmentEntity();
        departmentEntity.setName("PSYCHIATRY");
        departmentEntity.setDescription("Best department for counseling.");
        departmentEntity.setFloorNo(6);
        departmentRepository.save(departmentEntity);

        departmentEntity=new DepartmentEntity();
        departmentEntity.setName("RESPIRATORY MEDICINE");
        departmentEntity.setDescription("Respiratory diseases affect every part of the respiratory system, leading to lung disease and breathing problems.");
        departmentEntity.setFloorNo(2);
        departmentRepository.save(departmentEntity);

        departmentEntity=new DepartmentEntity();
        departmentEntity.setName("OPHTHALMOLOGY");
        departmentEntity.setDescription("This department provides both basic and specialized eye care services for patients.");
        departmentEntity.setFloorNo(7);
        departmentRepository.save(departmentEntity);

        departmentEntity=new DepartmentEntity();
        departmentEntity.setName("ORTHOPEDICS");
        departmentEntity.setDescription("The Department of Orthopaedics has two full-time Consultants, and a number of junior doctors, and supporting paramedical staff trained to deliver a 100% success rate.");
        departmentEntity.setFloorNo(6);
        departmentRepository.save(departmentEntity);

        departmentEntity=new DepartmentEntity();
        departmentEntity.setName("UROLOGY");
        departmentEntity.setDescription("Urology can be broadly defined as the specialty that manages patients with diseases of the male and female urinary tract.");
        departmentEntity.setFloorNo(6);
        departmentRepository.save(departmentEntity);
        return true;

    }
}
