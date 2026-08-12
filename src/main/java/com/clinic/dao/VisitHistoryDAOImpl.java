package com.clinic.dao;

import com.clinic.config.DatabaseConnection;
import com.clinic.dto.VisitHistory;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

public class VisitHistoryDAOImpl implements VisitHistoryDAO {

    @Override
    public int insertVisitHistory(VisitHistory visitHistory) {

        String sql = """
                INSERT INTO visit_history
                (appointment_id, diagnosis, prescription, visit_notes)
                VALUES (?, ?, ?, ?)
                """;

        try (
                Connection connection = DatabaseConnection.getConnection();
                java.sql.PreparedStatement statement =
                        connection.prepareStatement(sql,
                                java.sql.Statement.RETURN_GENERATED_KEYS)
        ) {

            statement.setInt(1, visitHistory.getAppointmentId());
            statement.setString(2, visitHistory.getDiagnosis());
            statement.setString(3, visitHistory.getPrescription());
            statement.setString(4, visitHistory.getVisitNotes());

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
    public VisitHistory getVisitHistoryById(int visitId) {

        String sql = "SELECT * FROM visit_history WHERE visit_id=?";

        try (
                Connection connection = DatabaseConnection.getConnection();
                java.sql.PreparedStatement statement =
                        connection.prepareStatement(sql)
        ) {

            statement.setInt(1, visitId);

            java.sql.ResultSet rs = statement.executeQuery();

            if (rs.next()) {

                VisitHistory visitHistory = new VisitHistory();

                visitHistory.setVisitId(rs.getInt("visit_id"));
                visitHistory.setAppointmentId(rs.getInt("appointment_id"));
                visitHistory.setDiagnosis(rs.getString("diagnosis"));
                visitHistory.setPrescription(rs.getString("prescription"));
                visitHistory.setVisitNotes(rs.getString("visit_notes"));

                return visitHistory;
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }


    @Override
    public List<VisitHistory> getAllVisitHistory() {

        List<VisitHistory> historyList = new ArrayList<>();

        String sql = "SELECT * FROM visit_history";

        try (
                Connection connection = DatabaseConnection.getConnection();
                java.sql.PreparedStatement statement =
                        connection.prepareStatement(sql);
                java.sql.ResultSet rs = statement.executeQuery()
        ) {

            while (rs.next()) {

                VisitHistory visitHistory = new VisitHistory();

                visitHistory.setVisitId(rs.getInt("visit_id"));
                visitHistory.setAppointmentId(rs.getInt("appointment_id"));
                visitHistory.setDiagnosis(rs.getString("diagnosis"));
                visitHistory.setPrescription(rs.getString("prescription"));
                visitHistory.setVisitNotes(rs.getString("visit_notes"));

                historyList.add(visitHistory);

            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return historyList;
    }

    @Override
    public boolean updateVisitHistory(VisitHistory visitHistory) {

        String sql = """
                UPDATE visit_history
                SET appointment_id=?,
                    diagnosis=?,
                    prescription=?,
                    visit_notes=?
                WHERE visit_id=?
                """;

        try (
                Connection connection = DatabaseConnection.getConnection();
                java.sql.PreparedStatement statement =
                        connection.prepareStatement(sql)
        ) {

            statement.setInt(1, visitHistory.getAppointmentId());
            statement.setString(2, visitHistory.getDiagnosis());
            statement.setString(3, visitHistory.getPrescription());
            statement.setString(4, visitHistory.getVisitNotes());
            statement.setInt(5, visitHistory.getVisitId());

            return statement.executeUpdate() > 0;

        } catch (Exception e) {
            e.printStackTrace();
        }

        return false;
    }

    @Override
    public boolean deleteVisitHistory(int visitId) {

        String sql = "DELETE FROM visit_history WHERE visit_id=?";

        try (
                Connection connection = DatabaseConnection.getConnection();
                java.sql.PreparedStatement statement =
                        connection.prepareStatement(sql)
        ) {

            statement.setInt(1, visitId);

            return statement.executeUpdate() > 0;

        } catch (Exception e) {
            e.printStackTrace();
        }

        return false;
    }
}
