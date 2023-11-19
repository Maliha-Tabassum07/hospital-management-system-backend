package com.maliha.pharmacymanagement.service;

import com.maliha.pharmacymanagement.entity.MedicineEntity;
import com.maliha.pharmacymanagement.entity.SymptomEntity;
import com.maliha.pharmacymanagement.model.MedicineDTO;
import com.maliha.pharmacymanagement.repository.MedicineRepository;
import com.maliha.pharmacymanagement.repository.SymptomRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class MedicineService {
    @Autowired
    private MedicineRepository medicineRepository;

    @Autowired
    private SymptomRepository symptomRepository;
    public MedicineEntity createMedicine(MedicineDTO medicineDTO) throws Exception {
        if(medicineRepository.findByMedicineName(medicineDTO.getMedicineName()).isPresent())
            throw new Exception("Record already exists");
        MedicineEntity medicineEntity = new MedicineEntity();
        medicineEntity.setMedicineName(medicineDTO.getMedicineName());
        medicineEntity.setGenericName(medicineDTO.getGenericName());
        medicineEntity.setMedicineType(medicineDTO.getMedicineType());
        medicineEntity.setManufacturer(medicineDTO.getManufacturer());
        medicineEntity.setConcentration(medicineDTO.getConcentration());
        medicineEntity.setUnitPrice(medicineDTO.getUnitPrice());
        medicineEntity.setExpirationDate(medicineDTO.getExpirationDate());
        medicineEntity.setSideEffect(medicineDTO.getSideEffect());
        for(Long id:medicineDTO.getSymptomList()){
            medicineEntity.getSymptom().add(symptomRepository.findById(id).orElseThrow(() -> new Exception()));
        }
        MedicineEntity storedMedicine =medicineRepository.save(medicineEntity);
        storedMedicine.setSpecialId(String.format("%s%02d", "M", storedMedicine.getId()));
        return medicineRepository.save(storedMedicine);
    }
    public List<MedicineEntity> getAllMedicine() throws Exception {
        return medicineRepository.findAll();
    }
    public List<MedicineEntity> getAllSymptomId(Long symptomId) throws Exception {
        return medicineRepository.findAllBySymptom(symptomRepository.findById(symptomId).orElseThrow(() -> new Exception()));
    }
    public List<MedicineEntity> getAllSymptomName(String symptomName) throws Exception {
        return medicineRepository.findAllBySymptom(symptomRepository.findByName(symptomName).orElseThrow(() -> new Exception()));
    }
    public MedicineEntity getByName(String medicineName) throws Exception {
        return medicineRepository.findByMedicineName(medicineName).orElseThrow(() -> new Exception());
    }
    public MedicineEntity getById(Long id) throws Exception {
        return medicineRepository.findById(id).orElseThrow(() -> new Exception());
    }
    public MedicineEntity getBySpecialId(String specialId) throws Exception {
        return medicineRepository.findByMedicineName(specialId).orElseThrow(() -> new Exception());
    }

    public MedicineEntity updateDoctor(MedicineDTO medicineDTO,Long id) throws Exception{
        MedicineEntity medicineEntity=medicineRepository.findById(id).orElseThrow(() -> new NullPointerException());
        medicineEntity.setMedicineName(medicineDTO.getMedicineName());
        medicineEntity.setGenericName(medicineDTO.getGenericName());
        medicineEntity.setMedicineType(medicineDTO.getMedicineType());
        medicineEntity.setManufacturer(medicineDTO.getManufacturer());
        medicineEntity.setConcentration(medicineDTO.getConcentration());
        medicineEntity.setUnitPrice(medicineDTO.getUnitPrice());
        medicineEntity.setExpirationDate(medicineDTO.getExpirationDate());
        medicineEntity.setSideEffect(medicineDTO.getSideEffect());
        for(Long symptomId:medicineDTO.getSymptomList()){
            medicineEntity.getSymptom().add(symptomRepository.findById(symptomId).orElseThrow(() -> new Exception()));
        }
        return medicineRepository.save(medicineEntity);
    }
    public Boolean deleteMedicine(Long id) throws Exception{
        if (medicineRepository.existsById(id)){
            medicineRepository.delete(medicineRepository.findById(id).get());
            return true;
        }
        return false;
    }

}
