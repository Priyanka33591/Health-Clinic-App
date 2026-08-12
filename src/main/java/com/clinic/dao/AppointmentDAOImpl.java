package com.clinic.dao;

import com.clinic.config.DatabaseConnection;
import com.clinic.dto.Appointment;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

public class AppointmentDAOImpl implements AppointmentDAO {

    @Override
    public int insertAppointment(Appointment appointment) {

        String sql = """
            INSERT INTO appointments
            (patient_id,doctor_id,appointment_date,status)
            VALUES (?,?,?,?)
            """;

        try (
                Connection connection = DatabaseConnection.getConnection();
                java.sql.PreparedStatement statement =
                        connection.prepareStatement(sql, java.sql.Statement.RETURN_GENERATED_KEYS)
        ) {

            statement.setInt(1, appointment.getPatientId());
            statement.setInt(2, appointment.getDoctorId());
            statement.setTimestamp(3, appointment.getAppointmentDate());
            statement.setString(4, appointment.getStatus());

            int rows = statement.executeUpdate();

            if (rows > 0) {

                java.sql.ResultSet rs = statement.getGeneratedKeys();

                if (rs.next()) {
                    return rs.getInt(1);
                }

            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return -1;
    }

    @Override
    public Appointment getAppointmentById(int appointmentId) {

        String sql = "SELECT * FROM appointments WHERE appointment_id=?";

        try (
                Connection connection = DatabaseConnection.getConnection();
                java.sql.PreparedStatement statement = connection.prepareStatement(sql)
        ) {

            statement.setInt(1, appointmentId);

            java.sql.ResultSet rs = statement.executeQuery();

            if (rs.next()) {

                Appointment appointment = new Appointment();

                appointment.setAppointmentId(rs.getInt("appointment_id"));
                appointment.setPatientId(rs.getInt("patient_id"));
                appointment.setDoctorId(rs.getInt("doctor_id"));
                appointment.setAppointmentDate(rs.getTimestamp("appointment_date"));
                appointment.setStatus(rs.getString("status"));

                return appointment;

            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    @Override
    public List<Appointment> getAllAppointments() {

        List<Appointment> appointments = new ArrayList<>();

        String sql = "SELECT * FROM appointments";

        try (
                Connection connection = DatabaseConnection.getConnection();
                java.sql.PreparedStatement statement = connection.prepareStatement(sql);
                java.sql.ResultSet rs = statement.executeQuery()
        ) {

            while (rs.next()) {

                Appointment appointment = new Appointment();

                appointment.setAppointmentId(rs.getInt("appointment_id"));
                appointment.setPatientId(rs.getInt("patient_id"));
                appointment.setDoctorId(rs.getInt("doctor_id"));
                appointment.setAppointmentDate(rs.getTimestamp("appointment_date"));
                appointment.setStatus(rs.getString("status"));

                appointments.add(appointment);

            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return appointments;
    }

    @Override
    public boolean updateAppointment(Appointment appointment) {

        String sql = """
            UPDATE appointments
            SET patient_id=?,
                doctor_id=?,
                appointment_date=?,
                status=?
            WHERE appointment_id=?
            """;

        try (
                Connection connection = DatabaseConnection.getConnection();
                java.sql.PreparedStatement statement = connection.prepareStatement(sql)
        ) {

            statement.setInt(1, appointment.getPatientId());
            statement.setInt(2, appointment.getDoctorId());
            statement.setTimestamp(3, appointment.getAppointmentDate());
            statement.setString(4, appointment.getStatus());
            statement.setInt(5, appointment.getAppointmentId());

            return statement.executeUpdate() > 0;

        } catch (Exception e) {
            e.printStackTrace();
        }

        return false;
    }

    @Override
    public boolean deleteAppointment(int appointmentId) {

        String sql = "DELETE FROM appointments WHERE appointment_id=?";

        try (
                Connection connection = DatabaseConnection.getConnection();
                java.sql.PreparedStatement statement = connection.prepareStatement(sql)
        ) {

            statement.setInt(1, appointmentId);

            return statement.executeUpdate() > 0;

        } catch (Exception e) {
            e.printStackTrace();
        }

        return false;
    }


}