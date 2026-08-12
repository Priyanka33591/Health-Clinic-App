package com.clinic.service;

import com.clinic.dao.PatientDAO;
import com.clinic.dao.PatientDAOImpl;
import com.clinic.dto.Patient;

import java.util.List;

public class PatientServiceImpl implements PatientService {

    private final PatientDAO patientDAO = new PatientDAOImpl();

    @Override
    public int registerPatient(Patient patient) {
        return patientDAO.insertPatient(patient);
    }

    @Override
    public Patient getPatientById(int patientId) {
        return patientDAO.getPatientById(patientId);
    }

    @Override
    public List<Patient> getAllPatients() {
        return patientDAO.getAllPatients();
    }

    @Override
    public boolean updatePatient(Patient patient) {
        return patientDAO.updatePatient(patient);
    }

    @Override
    public boolean deletePatient(int patientId) {
        return patientDAO.deletePatient(patientId);
    }
}