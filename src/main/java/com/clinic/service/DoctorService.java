package com.clinic.service;

import com.clinic.dto.Doctor;

import java.util.List;

public interface DoctorService {

    int registerDoctor(Doctor doctor);

    Doctor getDoctorById(int doctorId);

    List<Doctor> getAllDoctors();

    boolean updateDoctor(Doctor doctor);

    boolean deleteDoctor(int doctorId);

}