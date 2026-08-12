package com.clinic.dao;

import com.clinic.config.DatabaseConnection;
import com.clinic.dto.Patient;

import java.sql.Connection;

public class PatientDAOImpl implements PatientDAO {

    @Override
    public int insertPatient(Patient patient) {


        String sql = """
            INSERT INTO patients
            (first_name,last_name,date_of_birth,gender,phone_number,email,is_active)
            VALUES (?,?,?,?,?,?,?)
            """;

        try (
                Connection connection = DatabaseConnection.getConnection();
                java.sql.PreparedStatement statement =
                        connection.prepareStatement(sql, java.sql.Statement.RETURN_GENERATED_KEYS)
        ) {

            statement.setString(1, patient.getFirstName());
            statement.setString(2, patient.getLastName());
            statement.setDate(3, patient.getDateOfBirth());
            statement.setString(4, patient.getGender());
            statement.setString(5, patient.getPhoneNumber());
            statement.setString(6, patient.getEmail());
            statement.setBoolean(7, patient.isActive());

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
    public Patient getPatientById(int patientId) {

        String sql = "SELECT * FROM patients WHERE patient_id = ?";

        try (
                Connection connection = DatabaseConnection.getConnection();
                java.sql.PreparedStatement statement = connection.prepareStatement(sql)
        ) {

            statement.setInt(1, patientId);

            java.sql.ResultSet rs = statement.executeQuery();

            if (rs.next()) {

                Patient patient = new Patient();

                patient.setPatientId(rs.getInt("patient_id"));
                patient.setFirstName(rs.getString("first_name"));
                patient.setLastName(rs.getString("last_name"));
                patient.setDateOfBirth(rs.getDate("date_of_birth"));
                patient.setGender(rs.getString("gender"));
                patient.setPhoneNumber(rs.getString("phone_number"));
                patient.setEmail(rs.getString("email"));
                patient.setActive(rs.getBoolean("is_active"));
                patient.setRegisteredOn(rs.getTimestamp("registered_on"));

                return patient;
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    @Override
    public java.util.List<Patient> getAllPatients() {

        java.util.List<Patient> patients = new java.util.ArrayList<>();

        String sql = "SELECT * FROM patients";

        try (
                Connection connection = DatabaseConnection.getConnection();
                java.sql.PreparedStatement statement = connection.prepareStatement(sql);
                java.sql.ResultSet rs = statement.executeQuery()
        ) {

            while (rs.next()) {

                Patient patient = new Patient();

                patient.setPatientId(rs.getInt("patient_id"));
                patient.setFirstName(rs.getString("first_name"));
                patient.setLastName(rs.getString("last_name"));
                patient.setDateOfBirth(rs.getDate("date_of_birth"));
                patient.setGender(rs.getString("gender"));
                patient.setPhoneNumber(rs.getString("phone_number"));
                patient.setEmail(rs.getString("email"));
                patient.setActive(rs.getBoolean("is_active"));
                patient.setRegisteredOn(rs.getTimestamp("registered_on"));

                patients.add(patient);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return patients;
    }

    @Override
    public boolean updatePatient(Patient patient) {

        String sql = """
            UPDATE patients
            SET first_name=?,
                last_name=?,
                date_of_birth=?,
                gender=?,
                phone_number=?,
                email=?,
                is_active=?
            WHERE patient_id=?
            """;

        try (
                Connection connection = DatabaseConnection.getConnection();
                java.sql.PreparedStatement statement = connection.prepareStatement(sql)
        ) {

            statement.setString(1, patient.getFirstName());
            statement.setString(2, patient.getLastName());
            statement.setDate(3, patient.getDateOfBirth());
            statement.setString(4, patient.getGender());
            statement.setString(5, patient.getPhoneNumber());
            statement.setString(6, patient.getEmail());
            statement.setBoolean(7, patient.isActive());
            statement.setInt(8, patient.getPatientId());

            return statement.executeUpdate() > 0;

        } catch (Exception e) {
            e.printStackTrace();
        }

        return false;
    }

    @Override
    public boolean deletePatient(int patientId) {

        String sql = "DELETE FROM patients WHERE patient_id=?";

        try (
                Connection connection = DatabaseConnection.getConnection();
                java.sql.PreparedStatement statement = connection.prepareStatement(sql)
        ) {

            statement.setInt(1, patientId);

            return statement.executeUpdate() > 0;

        } catch (Exception e) {
            e.printStackTrace();
        }

        return false;
    }

}