package com.maliha.pharmacymanagement.service;

import com.maliha.pharmacymanagement.entity.SymptomEntity;
import com.maliha.pharmacymanagement.repository.MedicineRepository;
import com.maliha.pharmacymanagement.repository.SymptomRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PopulateSymptomService {
    @Autowired
    private SymptomRepository symptomRepository;

    public Boolean populateSymptom(){
        if (symptomRepository.existsById(1l)){
            return false;
        }
        SymptomEntity symptomEntity=new SymptomEntity();
        symptomEntity.setName("Fever");
        symptomRepository.save(symptomEntity);

        symptomEntity=new SymptomEntity();
        symptomEntity.setName("Cold");
        symptomRepository.save(symptomEntity);

        symptomEntity=new SymptomEntity();
        symptomEntity.setName("Headache");
        symptomRepository.save(symptomEntity);

        symptomEntity=new SymptomEntity();
        symptomEntity.setName("Hypertension");
        symptomRepository.save(symptomEntity);

        symptomEntity=new SymptomEntity();
        symptomEntity.setName("Diabetes");
        symptomRepository.save(symptomEntity);

        symptomEntity=new SymptomEntity();
        symptomEntity.setName("Acidity");
        symptomRepository.save(symptomEntity);

        symptomEntity=new SymptomEntity();
        symptomEntity.setName("Allergy");
        symptomRepository.save(symptomEntity);

        symptomEntity=new SymptomEntity();
        symptomEntity.setName("Viral Infection");
        symptomRepository.save(symptomEntity);

        symptomEntity=new SymptomEntity();
        symptomEntity.setName("Skin Infection");
        symptomRepository.save(symptomEntity);

        symptomEntity=new SymptomEntity();
        symptomEntity.setName("Pain");
        symptomRepository.save(symptomEntity);
        return true;

    }
}
