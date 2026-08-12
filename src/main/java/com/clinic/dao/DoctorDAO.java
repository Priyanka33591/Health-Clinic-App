package com.clinic.dao;

import com.clinic.dto.Doctor;

import java.util.List;

public interface DoctorDAO {

    int insertDoctor(Doctor doctor);

    Doctor getDoctorById(int doctorId);

    List<Doctor> getAllDoctors();

    boolean updateDoctor(Doctor doctor);

    boolean deleteDoctor(int doctorId);

}