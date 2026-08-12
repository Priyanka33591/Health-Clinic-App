package com.clinic.service;

import com.clinic.dto.Patient;

import java.util.List;

public interface PatientService {

    int registerPatient(Patient patient);

    Patient getPatientById(int patientId);

    List<Patient> getAllPatients();

    boolean updatePatient(Patient patient);

    boolean deletePatient(int patientId);

}