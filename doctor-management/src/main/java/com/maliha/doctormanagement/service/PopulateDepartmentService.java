package com.maliha.doctormanagement.service;

import com.maliha.doctormanagement.entity.DepartmentEntity;
import com.maliha.doctormanagement.repository.DepartmentRepository;
import org.springframework.beans.factory.annotation.Autowired;

public class PopulateDepartmentService {

    @Autowired
    private DepartmentRepository departmentRepository;

    public Boolean populateDepartment(){
        if (departmentRepository.existsById(1l)){
            return false;
        }
        DepartmentEntity departmentEntity=new DepartmentEntity();
        departmentEntity.setName("CARDIOLOGY");
        departmentEntity.setDescription("With the help of State-of-the-heart-technology, equipment and monitoring devices, our cardiologists are providing excellent heart care to all the patients.");
        departmentRepository.save(departmentEntity);

        departmentEntity=new DepartmentEntity();
        departmentEntity.setName("CARDIOLOGY");
        departmentEntity.setDescription("With the help of State-of-the-heart-technology, equipment and monitoring devices, our cardiologists are providing excellent heart care to all the patients.");
        departmentRepository.save(departmentEntity);

        departmentEntity=new DepartmentEntity();
        departmentEntity.setName("CARDIOLOGY");
        departmentEntity.setDescription("With the help of State-of-the-heart-technology, equipment and monitoring devices, our cardiologists are providing excellent heart care to all the patients.");
        departmentRepository.save(departmentEntity);

        departmentEntity=new DepartmentEntity();
        departmentEntity.setName("CARDIOLOGY");
        departmentEntity.setDescription("With the help of State-of-the-heart-technology, equipment and monitoring devices, our cardiologists are providing excellent heart care to all the patients.");
        departmentRepository.save(departmentEntity);

        departmentEntity=new DepartmentEntity();
        departmentEntity.setName("CARDIOLOGY");
        departmentEntity.setDescription("With the help of State-of-the-heart-technology, equipment and monitoring devices, our cardiologists are providing excellent heart care to all the patients.");
        departmentRepository.save(departmentEntity);

        departmentEntity=new DepartmentEntity();
        departmentEntity.setName("CARDIOLOGY");
        departmentEntity.setDescription("With the help of State-of-the-heart-technology, equipment and monitoring devices, our cardiologists are providing excellent heart care to all the patients.");
        departmentRepository.save(departmentEntity);

        departmentEntity=new DepartmentEntity();
        departmentEntity.setName("CARDIOLOGY");
        departmentEntity.setDescription("With the help of State-of-the-heart-technology, equipment and monitoring devices, our cardiologists are providing excellent heart care to all the patients.");
        departmentRepository.save(departmentEntity);

        departmentEntity=new DepartmentEntity();
        departmentEntity.setName("CARDIOLOGY");
        departmentEntity.setDescription("With the help of State-of-the-heart-technology, equipment and monitoring devices, our cardiologists are providing excellent heart care to all the patients.");
        departmentRepository.save(departmentEntity);

        departmentEntity=new DepartmentEntity();
        departmentEntity.setName("CARDIOLOGY");
        departmentEntity.setDescription("With the help of State-of-the-heart-technology, equipment and monitoring devices, our cardiologists are providing excellent heart care to all the patients.");
        departmentRepository.save(departmentEntity);

        departmentEntity=new DepartmentEntity();
        departmentEntity.setName("CARDIOLOGY");
        departmentEntity.setDescription("With the help of State-of-the-heart-technology, equipment and monitoring devices, our cardiologists are providing excellent heart care to all the patients.");
        departmentRepository.save(departmentEntity);

        departmentEntity=new DepartmentEntity();
        departmentEntity.setName("CARDIOLOGY");
        departmentEntity.setDescription("With the help of State-of-the-heart-technology, equipment and monitoring devices, our cardiologists are providing excellent heart care to all the patients.");
        departmentRepository.save(departmentEntity);

        departmentEntity=new DepartmentEntity();
        departmentEntity.setName("CARDIOLOGY");
        departmentEntity.setDescription("With the help of State-of-the-heart-technology, equipment and monitoring devices, our cardiologists are providing excellent heart care to all the patients.");
        departmentRepository.save(departmentEntity);

        departmentEntity=new DepartmentEntity();
        departmentEntity.setName("CARDIOLOGY");
        departmentEntity.setDescription("With the help of State-of-the-heart-technology, equipment and monitoring devices, our cardiologists are providing excellent heart care to all the patients.");
        departmentRepository.save(departmentEntity);

        departmentEntity=new DepartmentEntity();
        departmentEntity.setName("CARDIOLOGY");
        departmentEntity.setDescription("With the help of State-of-the-heart-technology, equipment and monitoring devices, our cardiologists are providing excellent heart care to all the patients.");
        departmentRepository.save(departmentEntity);

        departmentEntity=new DepartmentEntity();
        departmentEntity.setName("CARDIOLOGY");
        departmentEntity.setDescription("With the help of State-of-the-heart-technology, equipment and monitoring devices, our cardiologists are providing excellent heart care to all the patients.");
        departmentRepository.save(departmentEntity);

        departmentEntity=new DepartmentEntity();
        departmentEntity.setName("CARDIOLOGY");
        departmentEntity.setDescription("With the help of State-of-the-heart-technology, equipment and monitoring devices, our cardiologists are providing excellent heart care to all the patients.");
        departmentRepository.save(departmentEntity);

        departmentEntity=new DepartmentEntity();
        departmentEntity.setName("CARDIOLOGY");
        departmentEntity.setDescription("With the help of State-of-the-heart-technology, equipment and monitoring devices, our cardiologists are providing excellent heart care to all the patients.");
        departmentRepository.save(departmentEntity);

        departmentEntity=new DepartmentEntity();
        departmentEntity.setName("CARDIOLOGY");
        departmentEntity.setDescription("With the help of State-of-the-heart-technology, equipment and monitoring devices, our cardiologists are providing excellent heart care to all the patients.");
        departmentRepository.save(departmentEntity);
        return true;

    }
}
