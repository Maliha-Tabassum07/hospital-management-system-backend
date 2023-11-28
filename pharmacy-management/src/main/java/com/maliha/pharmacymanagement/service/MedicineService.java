package com.maliha.pharmacymanagement.service;

import com.maliha.pharmacymanagement.entity.MedicineEntity;
import com.maliha.pharmacymanagement.entity.SymptomEntity;
import com.maliha.pharmacymanagement.exception.CustomException;
import com.maliha.pharmacymanagement.model.MedicineDTO;
import com.maliha.pharmacymanagement.repository.MedicineRepository;
import com.maliha.pharmacymanagement.repository.SymptomRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
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
            throw new CustomException("Record already exists");
        MedicineEntity medicineEntity = new MedicineEntity();
        medicineEntity.setMedicineName(medicineDTO.getMedicineName());
        medicineEntity.setGenericName(medicineDTO.getGenericName());
        medicineEntity.setMedicineType(medicineDTO.getMedicineType());
        medicineEntity.setManufacturer(medicineDTO.getManufacturer());
        medicineEntity.setConcentration(medicineDTO.getConcentration());
        medicineEntity.setUnitPrice(medicineDTO.getUnitPrice());
        medicineEntity.setExpirationDate(LocalDate.now().plusYears(3));
        medicineEntity.setSideEffect(medicineDTO.getSideEffect());
        List<SymptomEntity> symptomEntityList=new ArrayList<>();
        for(Long id:medicineDTO.getSymptomList()){
            symptomEntityList.add(symptomRepository.findById(id).orElseThrow(() -> new Exception()));
        }
        medicineEntity.setSymptom(symptomEntityList);
        MedicineEntity storedMedicine =medicineRepository.save(medicineEntity);
        storedMedicine.setSpecialId(String.format("%s%02d", "M", storedMedicine.getId()));
        return medicineRepository.save(storedMedicine);
    }
    public List<MedicineEntity> getAllMedicine() throws Exception {
        return medicineRepository.findAll();
    }
    public List<String> getAllSymptomId(Long symptomId) throws Exception {
        List<String> medicineName=new ArrayList<>();
        for (MedicineEntity medicineEntity:medicineRepository.findAllBySymptom(symptomRepository.findById(symptomId).orElseThrow(() -> new Exception()))){
            medicineName.add(medicineEntity.getMedicineName());
        }
        return medicineName;
    }
    public List<MedicineEntity> getAllBySymptomName(String symptomName) throws Exception {
        return medicineRepository.findAllBySymptom(symptomRepository.findByName(symptomName).orElseThrow(() -> new Exception()));
    }
    public MedicineEntity getByName(String medicineName) throws Exception {
        return medicineRepository.findByMedicineName(medicineName).orElseThrow(() -> new Exception());
    }
    public MedicineEntity getById(Integer id) throws Exception {
        return medicineRepository.findById(id).orElseThrow(() -> new Exception());
    }
    public MedicineEntity getBySpecialId(String specialId) throws Exception {
        System.out.println("hello");
        return medicineRepository.findBySpecialId(specialId).orElseThrow(() -> new Exception());
    }

    public MedicineEntity updateMedicine(MedicineDTO medicineDTO,Integer id) throws Exception{
        MedicineEntity medicineEntity=medicineRepository.findById(id).orElseThrow(() -> new CustomException("Not found"));
        medicineEntity.setMedicineName(medicineDTO.getMedicineName());
        medicineEntity.setGenericName(medicineDTO.getGenericName());
        medicineEntity.setMedicineType(medicineDTO.getMedicineType());
        medicineEntity.setManufacturer(medicineDTO.getManufacturer());
        medicineEntity.setConcentration(medicineDTO.getConcentration());
        medicineEntity.setUnitPrice(medicineDTO.getUnitPrice());
        medicineEntity.setSideEffect(medicineDTO.getSideEffect());
        List<SymptomEntity> symptomEntityList=new ArrayList<>();
        for(Long symptomId:medicineDTO.getSymptomList()){
            symptomEntityList.add(symptomRepository.findById(symptomId).orElseThrow(() -> new Exception()));
        }
        medicineEntity.setSymptom(symptomEntityList);
        return medicineRepository.save(medicineEntity);
    }
    public Boolean deleteMedicine(Integer id) throws Exception{
        if (medicineRepository.existsById(id)){
            medicineRepository.delete(medicineRepository.findById(id).get());
            return true;
        }
        return false;
    }
    public List<MedicineEntity> medicineHelpDesk(String input)throws Exception{
        if(medicineRepository.existsByMedicineName(input)){
            return medicineRepository.findAllByMedicineName(input);
        }
        else if(medicineRepository.existsByGenericName(input)){
            return medicineRepository.findAllByGenericName(input);
        }
        else if (medicineRepository.existsBySpecialId(input)){
            return medicineRepository.findAllBySpecialId(input);
        }
        else if (symptomRepository.existsByName(input)){
            return getAllBySymptomName(input);
        }
        else if (medicineRepository.existsByMedicineType(input)){
            return medicineRepository.findAllByMedicineType(input);
        }
        else if (medicineRepository.existsByManufacturer(input)){
            return medicineRepository.findAllByManufacturer(input);
        }
        throw new CustomException("Wrong Input");
    }


}
