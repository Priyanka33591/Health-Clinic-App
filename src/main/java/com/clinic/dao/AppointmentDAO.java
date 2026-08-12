package com.clinic.dao;

import com.clinic.dto.Appointment;

import java.util.List;

public interface AppointmentDAO {

    int insertAppointment(Appointment appointment);

    Appointment getAppointmentById(int appointmentId);

    List<Appointment> getAllAppointments();

    boolean updateAppointment(Appointment appointment);

    boolean deleteAppointment(int appointmentId);

}