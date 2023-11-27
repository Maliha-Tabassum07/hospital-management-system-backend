package com.maliha.patientmanagement;

import com.maliha.patientmanagement.controller.PatientController;
import com.maliha.patientmanagement.entity.PatientEntity;
import com.maliha.patientmanagement.model.PatientDTO;
import com.maliha.patientmanagement.model.PatientViewDTO;
import com.maliha.patientmanagement.service.PatientService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;


import java.util.Collections;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

public class PatientControllerTest {
    @Mock
    private PatientService patientService;
    @InjectMocks
    private PatientController patientController;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testUpdateProfile_Success() throws Exception {
        // Given
        PatientDTO patientDTO = new PatientDTO();
        patientDTO.setEmail("test@example.com");
        // Set other necessary fields of patientDTO

        when(patientService.updatePatientProfile(any(PatientDTO.class))).thenReturn(new PatientEntity());

        // When
        ResponseEntity<?> response = patientController.updateProfile(patientDTO);

        // Then
        assertNotNull(response);
        assertEquals(HttpStatus.ACCEPTED, response.getStatusCode());
        verify(patientService, times(1)).updatePatientProfile(any(PatientDTO.class));
    }

    @Test
    public void testViewProfile_Success() throws Exception {
        // Given
        when(patientService.getSelfInfo()).thenReturn(new PatientViewDTO());

        // When
        ResponseEntity<?> response = patientController.viewProfile();

        // Then
        assertNotNull(response);
        assertEquals(HttpStatus.ACCEPTED, response.getStatusCode());
    }

    @Test
    public void testGetAllPatient_Success() {
        // Given
        when(patientService.getAllPatient()).thenReturn(Collections.emptyList());

        // When
        ResponseEntity<?> response = patientController.getAllPatient();

        // Then
        assertNotNull(response);
        assertEquals(HttpStatus.ACCEPTED, response.getStatusCode());
    }

}
