package com.maliha.pharmacymanagement;

import com.maliha.pharmacymanagement.entity.MedicineEntity;
import com.maliha.pharmacymanagement.repository.MedicineRepository;
import com.maliha.pharmacymanagement.repository.SymptomRepository;
import com.maliha.pharmacymanagement.service.MedicineService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class MedicineServiceTest {

    @Mock
    private MedicineRepository medicineRepository;
    @Mock
    private SymptomRepository symptomRepository;

    @InjectMocks
    private MedicineService medicineService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testMedicineHelpDesk_FoundByMedicineName() throws Exception {
        String input = "Paracetamol";
        MedicineEntity medicine = new MedicineEntity();
        medicine.setMedicineName(input);
        List<MedicineEntity> expectedMedicines = Collections.singletonList(medicine);

        when(medicineRepository.existsByMedicineName(input)).thenReturn(true);
        when(medicineRepository.findAllByMedicineName(input)).thenReturn(expectedMedicines);

        List<MedicineEntity> result = medicineService.medicineHelpDesk(input);

        assertNotNull(result);
        assertFalse(result.isEmpty());
        assertEquals(input, result.get(0).getMedicineName());
        verify(medicineRepository, times(1)).findAllByMedicineName(input);
    }

    @Test
    public void testMedicineHelpDesk_FoundByGenericName() throws Exception {
        String input = "Acetaminophen";
        MedicineEntity medicine = new MedicineEntity();
        medicine.setGenericName(input);
        List<MedicineEntity> expectedMedicines = Collections.singletonList(medicine);

        when(medicineRepository.existsByGenericName(input)).thenReturn(true);
        when(medicineRepository.findAllByGenericName(input)).thenReturn(expectedMedicines);

        List<MedicineEntity> result = medicineService.medicineHelpDesk(input);

        assertNotNull(result);
        assertFalse(result.isEmpty());
        assertEquals(input, result.get(0).getGenericName());
        verify(medicineRepository, times(1)).findAllByGenericName(input);
    }

    @Test
    public void testMedicineHelpDesk_FoundBySpecialId() throws Exception {
        String input = "med123";
        MedicineEntity medicine = new MedicineEntity();
        medicine.setSpecialId(input);
        List<MedicineEntity> expectedMedicines = Collections.singletonList(medicine);

        when(medicineRepository.existsBySpecialId(input)).thenReturn(true);
        when(medicineRepository.findAllBySpecialId(input)).thenReturn(expectedMedicines);

        List<MedicineEntity> result = medicineService.medicineHelpDesk(input);

        assertNotNull(result);
        assertFalse(result.isEmpty());
        assertEquals(input, result.get(0).getSpecialId());
        verify(medicineRepository, times(1)).findAllBySpecialId(input);
    }


    @Test
    public void testMedicineHelpDesk_FoundByMedicineType() throws Exception {
        String input = "Analgesic";
        MedicineEntity medicine = new MedicineEntity();
        medicine.setMedicineType(input);
        List<MedicineEntity> expectedMedicines = Collections.singletonList(medicine);

        when(medicineRepository.existsByMedicineType(input)).thenReturn(true);
        when(medicineRepository.findAllByMedicineType(input)).thenReturn(expectedMedicines);

        List<MedicineEntity> result = medicineService.medicineHelpDesk(input);

        assertNotNull(result);
        assertFalse(result.isEmpty());
        assertEquals(input, result.get(0).getMedicineType());
        verify(medicineRepository, times(1)).findAllByMedicineType(input);
    }

    @Test
    public void testMedicineHelpDesk_FoundByManufacturer() throws Exception {
        String input = "Pharma Inc.";
        MedicineEntity medicine = new MedicineEntity();
        medicine.setManufacturer(input);
        List<MedicineEntity> expectedMedicines = Collections.singletonList(medicine);

        when(medicineRepository.existsByManufacturer(input)).thenReturn(true);
        when(medicineRepository.findAllByManufacturer(input)).thenReturn(expectedMedicines);

        List<MedicineEntity> result = medicineService.medicineHelpDesk(input);

        assertNotNull(result);
        assertFalse(result.isEmpty());
        assertEquals(input, result.get(0).getManufacturer());
        verify(medicineRepository, times(1)).findAllByManufacturer(input);
    }

    @Test
    public void testMedicineHelpDesk_WrongInput_ThrowsException() {
        String input = "unknown";
        when(medicineRepository.existsByMedicineName(input)).thenReturn(false);
        when(medicineRepository.existsByGenericName(input)).thenReturn(false);
        when(medicineRepository.existsBySpecialId(input)).thenReturn(false);
        when(symptomRepository.existsByName(input)).thenReturn(false);
        when(medicineRepository.existsByMedicineType(input)).thenReturn(false);
        when(medicineRepository.existsByManufacturer(input)).thenReturn(false);

        assertThrows(Exception.class, () -> medicineService.medicineHelpDesk(input));
    }
}
