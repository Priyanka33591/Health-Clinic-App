package com.clinic.service;

import com.clinic.dao.DoctorDAO;
import com.clinic.dao.DoctorDAOImpl;
import com.clinic.dto.Doctor;

import java.util.List;

public class DoctorServiceImpl implements DoctorService {

    private final DoctorDAO doctorDAO = new DoctorDAOImpl();

    @Override
    public int registerDoctor(Doctor doctor) {
        return doctorDAO.insertDoctor(doctor);
    }

    @Override
    public Doctor getDoctorById(int doctorId) {
        return doctorDAO.getDoctorById(doctorId);
    }

    @Override
    public List<Doctor> getAllDoctors() {
        return doctorDAO.getAllDoctors();
    }

    @Override
    public boolean updateDoctor(Doctor doctor) {
        return doctorDAO.updateDoctor(doctor);
    }

    @Override
    public boolean deleteDoctor(int doctorId) {
        return doctorDAO.deleteDoctor(doctorId);
    }
}