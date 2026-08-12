package com.clinic.service;

import com.clinic.dao.AppointmentDAO;
import com.clinic.dao.AppointmentDAOImpl;
import com.clinic.dto.Appointment;

import java.util.List;

public class AppointmentService {

    private final AppointmentDAO appointmentDAO = new AppointmentDAOImpl();

    public int bookAppointment(Appointment appointment) {
        return appointmentDAO.insertAppointment(appointment);
    }

    public Appointment getAppointmentById(int appointmentId) {
        return appointmentDAO.getAppointmentById(appointmentId);
    }

    public List<Appointment> getAllAppointments() {
        return appointmentDAO.getAllAppointments();
    }

    public boolean updateAppointment(Appointment appointment) {
        return appointmentDAO.updateAppointment(appointment);
    }

    public boolean cancelAppointment(int appointmentId) {
        return appointmentDAO.deleteAppointment(appointmentId);
    }
}