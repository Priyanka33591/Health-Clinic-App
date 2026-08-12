package com.clinic.dao;

import com.clinic.config.DatabaseConnection;
import com.clinic.dto.Doctor;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

public class DoctorDAOImpl implements DoctorDAO {

    @Override
    public int insertDoctor(Doctor doctor) {

        String sql = """
                
                    INSERT INTO doctors
                (first_name,last_name,phone_number,email,is_active)
                VALUES (?,?,?,?,?)
                """;

        try (Connection connection = DatabaseConnection.getConnection(); java.sql.PreparedStatement statement = connection.prepareStatement(sql, java.sql.Statement.RETURN_GENERATED_KEYS)) {

            statement.setString(1, doctor.getFirstName());
            statement.setString(2, doctor.getLastName());
            statement.setString(3, doctor.getPhoneNumber());
            statement.setString(4, doctor.getEmail());
            statement.setBoolean(5, doctor.isActive());

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
    public Doctor getDoctorById(int doctorId) {

        String sql = "SELECT * FROM doctors WHERE doctor_id=?";

        try (Connection connection = DatabaseConnection.getConnection(); java.sql.PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setInt(1, doctorId);

            java.sql.ResultSet rs = statement.executeQuery();

            if (rs.next()) {

                Doctor doctor = new Doctor();

                doctor.setDoctorId(rs.getInt("doctor_id"));
                doctor.setFirstName(rs.getString("first_name"));
                doctor.setLastName(rs.getString("last_name"));
                doctor.setPhoneNumber(rs.getString("phone_number"));
                doctor.setEmail(rs.getString("email"));
                doctor.setActive(rs.getBoolean("is_active"));

                return doctor;
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    @Override
    public List<Doctor> getAllDoctors() {

        List<Doctor> doctors = new ArrayList<>();

        String sql = "SELECT * FROM doctors";

        try (Connection connection = DatabaseConnection.getConnection(); java.sql.PreparedStatement statement = connection.prepareStatement(sql); java.sql.ResultSet rs = statement.executeQuery()) {

            while (rs.next()) {

                Doctor doctor = new Doctor();

                doctor.setDoctorId(rs.getInt("doctor_id"));
                doctor.setFirstName(rs.getString("first_name"));
                doctor.setLastName(rs.getString("last_name"));
                doctor.setPhoneNumber(rs.getString("phone_number"));
                doctor.setEmail(rs.getString("email"));
                doctor.setActive(rs.getBoolean("is_active"));

                doctors.add(doctor);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return doctors;
    }

    @Override
    public boolean updateDoctor(Doctor doctor) {

        String sql = """
                
                    UPDATE
                            SET firs
                                 last_n
                              phone_number=?,
                
                                  is_
                            WHERE doctor_id=?
                """;

        try (Connection connection = DatabaseConnection.getConnection(); java.sql.PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setString(1, doctor.getFirstName());
            statement.setString(2, doctor.getLastName());
            statement.setString(3, doctor.getPhoneNumber());
            statement.setString(4, doctor.getEmail());
            statement.setBoolean(5, doctor.isActive());
            statement.setInt(6, doctor.getDoctorId());

            return statement.executeUpdate() > 0;

        } catch (Exception e) {
            e.printStackTrace();
        }

        return false;
    }

    @Override
    public boolean deleteDoctor(int doctorId) {

        String sql = "DELETE FROM doctors WHERE doctor_id=?";

        try (Connection connection = DatabaseConnection.getConnection(); java.sql.PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setInt(1, doctorId);

            return statement.executeUpdate() > 0;

        } catch (Exception e) {
            e.printStackTrace();
        }

        return false;
    }

}
