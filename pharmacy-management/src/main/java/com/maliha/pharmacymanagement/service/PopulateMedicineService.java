package com.maliha.pharmacymanagement.service;


import com.maliha.pharmacymanagement.entity.MedicineEntity;
import com.maliha.pharmacymanagement.entity.SymptomEntity;
import com.maliha.pharmacymanagement.repository.MedicineRepository;
import com.maliha.pharmacymanagement.repository.SymptomRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
public class PopulateMedicineService {
    @Autowired
    private SymptomRepository symptomRepository;
    @Autowired
    private MedicineRepository medicineRepository;

    public Boolean populateMedicine(){
        if (medicineRepository.existsById(1l)){
            return false;
        }
        MedicineEntity medicineEntity=new MedicineEntity();
        List<SymptomEntity> symptomEntityList=new ArrayList<>();
        medicineEntity.setMedicineName("Panadol");
        medicineEntity.setGenericName("Paracetamol");
        medicineEntity.setMedicineType("Tablet");
        medicineEntity.setManufacturer("Square Pharmaceuticals Ltd");
        medicineEntity.setConcentration("500mg");
        medicineEntity.setUnitPrice("2.50");
        medicineEntity.setExpirationDate(LocalDate.now().plusYears(3));
        medicineEntity.setSideEffect("Upset stomach");
        symptomEntityList.add(symptomRepository.findById(1l).get());
        symptomEntityList.add(symptomRepository.findById(10l).get());
        medicineEntity.setSymptom(symptomEntityList);
        MedicineEntity storedMedicine =medicineRepository.save(medicineEntity);
        storedMedicine.setSpecialId(String.format("%s%02d", "M", storedMedicine.getId()));
        medicineRepository.save(storedMedicine);

        medicineEntity=new MedicineEntity();
        symptomEntityList=new ArrayList<>();
        medicineEntity.setMedicineName("Cephalexin");
        medicineEntity.setGenericName("Cephalexin");
        medicineEntity.setMedicineType("Tablet");
        medicineEntity.setManufacturer("Renata Limited");
        medicineEntity.setConcentration("250mg");
        medicineEntity.setUnitPrice("3.00");
        medicineEntity.setExpirationDate(LocalDate.now().plusYears(3));
        medicineEntity.setSideEffect("Diarrhea");
        symptomEntityList.add(symptomRepository.findById(9l).get());
        symptomEntityList.add(symptomRepository.findById(8l).get());
        medicineEntity.setSymptom(symptomEntityList);
        storedMedicine =medicineRepository.save(medicineEntity);
        storedMedicine.setSpecialId(String.format("%s%02d", "M", storedMedicine.getId()));
        medicineRepository.save(storedMedicine);

        medicineEntity=new MedicineEntity();
        symptomEntityList=new ArrayList<>();
        medicineEntity.setMedicineName("Enam");
        medicineEntity.setGenericName("Amlodipine");
        medicineEntity.setMedicineType("Tablet");
        medicineEntity.setManufacturer("ACI Limited");
        medicineEntity.setConcentration("5mg");
        medicineEntity.setUnitPrice("4.00");
        medicineEntity.setExpirationDate(LocalDate.now().plusYears(3));
        medicineEntity.setSideEffect("Swelling of ankles and feet");
        symptomEntityList.add(symptomRepository.findById(4l).get());
        medicineEntity.setSymptom(symptomEntityList);
        storedMedicine =medicineRepository.save(medicineEntity);
        storedMedicine.setSpecialId(String.format("%s%02d", "M", storedMedicine.getId()));
        medicineRepository.save(storedMedicine);

        medicineEntity=new MedicineEntity();
        symptomEntityList=new ArrayList<>();
        medicineEntity.setMedicineName("Ambroxol");
        medicineEntity.setGenericName("Ambroxol");
        medicineEntity.setMedicineType("Syrup");
        medicineEntity.setManufacturer("Incepta Pharmaceuticals Ltd.");
        medicineEntity.setConcentration("30mg");
        medicineEntity.setUnitPrice("5.50");
        medicineEntity.setExpirationDate(LocalDate.now().plusYears(3));
        medicineEntity.setSideEffect("None");
        symptomEntityList.add(symptomRepository.findById(2l).get());
        medicineEntity.setSymptom(symptomEntityList);
        storedMedicine =medicineRepository.save(medicineEntity);
        storedMedicine.setSpecialId(String.format("%s%02d", "M", storedMedicine.getId()));
        medicineRepository.save(storedMedicine);

        medicineEntity=new MedicineEntity();
        symptomEntityList=new ArrayList<>();
        medicineEntity.setMedicineName("NovoRapid");
        medicineEntity.setGenericName("Insulin Aspart");
        medicineEntity.setMedicineType("Injection");
        medicineEntity.setManufacturer("Beximco Pharmaceuticals Ltd.");
        medicineEntity.setConcentration("100 units");
        medicineEntity.setUnitPrice("150.00");
        medicineEntity.setExpirationDate(LocalDate.now().plusYears(3));
        medicineEntity.setSideEffect("Hypoglycemia");
        symptomEntityList.add(symptomRepository.findById(5l).get());
        medicineEntity.setSymptom(symptomEntityList);
        storedMedicine =medicineRepository.save(medicineEntity);
        storedMedicine.setSpecialId(String.format("%s%02d", "M", storedMedicine.getId()));
        medicineRepository.save(storedMedicine);

        medicineEntity=new MedicineEntity();
        symptomEntityList=new ArrayList<>();
        medicineEntity.setMedicineName("Atarax");
        medicineEntity.setGenericName("Hydroxyzine");
        medicineEntity.setMedicineType("Tablet");
        medicineEntity.setManufacturer("Square Pharmaceuticals Ltd.");
        medicineEntity.setConcentration("25mg");
        medicineEntity.setUnitPrice("6.50");
        medicineEntity.setExpirationDate(LocalDate.now().plusYears(3));
        medicineEntity.setSideEffect("Drowsiness");
        symptomEntityList.add(symptomRepository.findById(7l).get());
        medicineEntity.setSymptom(symptomEntityList);
        storedMedicine =medicineRepository.save(medicineEntity);
        storedMedicine.setSpecialId(String.format("%s%02d", "M", storedMedicine.getId()));
        medicineRepository.save(storedMedicine);

        medicineEntity=new MedicineEntity();
        symptomEntityList=new ArrayList<>();
        medicineEntity.setMedicineName("Amoxicillin");
        medicineEntity.setGenericName("Amoxicillin");
        medicineEntity.setMedicineType("Tablet");
        medicineEntity.setManufacturer("Renata Limited");
        medicineEntity.setConcentration("500mg");
        medicineEntity.setUnitPrice("4.50");
        medicineEntity.setExpirationDate(LocalDate.now().plusYears(3));
        medicineEntity.setSideEffect("Diarrhea");
        symptomEntityList.add(symptomRepository.findById(8l).get());
        medicineEntity.setSymptom(symptomEntityList);
        storedMedicine =medicineRepository.save(medicineEntity);
        storedMedicine.setSpecialId(String.format("%s%02d", "M", storedMedicine.getId()));
        medicineRepository.save(storedMedicine);


        medicineEntity=new MedicineEntity();
        symptomEntityList=new ArrayList<>();
        medicineEntity.setMedicineName("Metacin");
        medicineEntity.setGenericName("Paracetamol");
        medicineEntity.setMedicineType("Syrup");
        medicineEntity.setManufacturer("ACI Limited");
        medicineEntity.setConcentration("125mg");
        medicineEntity.setUnitPrice("3.00");
        medicineEntity.setExpirationDate(LocalDate.now().plusYears(3));
        medicineEntity.setSideEffect("Upset stomach");
        symptomEntityList.add(symptomRepository.findById(1l).get());
        symptomEntityList.add(symptomRepository.findById(10l).get());
        medicineEntity.setSymptom(symptomEntityList);
        storedMedicine =medicineRepository.save(medicineEntity);
        storedMedicine.setSpecialId(String.format("%s%02d", "M", storedMedicine.getId()));
        medicineRepository.save(storedMedicine);


        medicineEntity=new MedicineEntity();
        symptomEntityList=new ArrayList<>();
        medicineEntity.setMedicineName("Erythromycin");
        medicineEntity.setGenericName("Erythromycin");
        medicineEntity.setMedicineType("Tablet");
        medicineEntity.setManufacturer("Incepta Pharmaceuticals Ltd.");
        medicineEntity.setConcentration("250mg");
        medicineEntity.setUnitPrice("5.00");
        medicineEntity.setExpirationDate(LocalDate.now().plusYears(3));
        medicineEntity.setSideEffect("Nausea");
        symptomEntityList.add(symptomRepository.findById(8l).get());
        medicineEntity.setSymptom(symptomEntityList);
        storedMedicine =medicineRepository.save(medicineEntity);
        storedMedicine.setSpecialId(String.format("%s%02d", "M", storedMedicine.getId()));
        medicineRepository.save(storedMedicine);


        return true;

    }
}

