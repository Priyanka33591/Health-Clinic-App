package com.clinic.dao;

import com.clinic.config.DatabaseConnection;
import com.clinic.dto.Billing;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

public class BillingDAOImpl implements BillingDAO {

    @Override
    public int insertBill(Billing billing) {

        String sql = """
        INSERT INTO billing
        (appointment_id,amount,payment_status)
        VALUES(?,?,?)
        """;

        try(
                Connection connection = DatabaseConnection.getConnection();
                java.sql.PreparedStatement statement =
                        connection.prepareStatement(sql,
                                java.sql.Statement.RETURN_GENERATED_KEYS)
        ){

            statement.setInt(1,billing.getAppointmentId());
            statement.setBigDecimal(2,billing.getAmount());
            statement.setString(3,billing.getPaymentStatus());

            int rows = statement.executeUpdate();

            if(rows>0){

                java.sql.ResultSet rs = statement.getGeneratedKeys();

                if(rs.next())
                    return rs.getInt(1);
            }

        }catch(Exception e){
            e.printStackTrace();
        }

        return -1;
    }


    @Override
    public Billing getBillById(int billId) {

        String sql="SELECT * FROM billing WHERE bill_id=?";

        try(
                Connection connection=DatabaseConnection.getConnection();
                java.sql.PreparedStatement statement=connection.prepareStatement(sql)
        ){

            statement.setInt(1,billId);

            java.sql.ResultSet rs=statement.executeQuery();

            if(rs.next()){

                Billing billing=new Billing();

                billing.setBillId(rs.getInt("bill_id"));
                billing.setAppointmentId(rs.getInt("appointment_id"));
                billing.setAmount(rs.getBigDecimal("amount"));
                billing.setPaymentStatus(rs.getString("payment_status"));
                billing.setGeneratedOn(rs.getTimestamp("billing_date"));

                return billing;
            }

        }catch(Exception e){
            e.printStackTrace();
        }

        return null;
    }

    @Override
    public List<Billing> getAllBills() {

        List<Billing> bills=new ArrayList<>();

        String sql="SELECT * FROM billing";

        try(
                Connection connection=DatabaseConnection.getConnection();
                java.sql.PreparedStatement statement=connection.prepareStatement(sql);
                java.sql.ResultSet rs=statement.executeQuery()
        ){

            while(rs.next()){

                Billing billing=new Billing();

                billing.setBillId(rs.getInt("bill_id"));
                billing.setAppointmentId(rs.getInt("appointment_id"));
                billing.setAmount(rs.getBigDecimal("amount"));
                billing.setPaymentStatus(rs.getString("payment_status"));
                billing.setGeneratedOn(rs.getTimestamp("billing_date"));

                bills.add(billing);

            }

        }catch(Exception e){
            e.printStackTrace();
        }

        return bills;
    }

    @Override
    public boolean updateBill(Billing billing) {

        String sql="""
        UPDATE billing
        SET appointment_id=?,
            amount=?,
            payment_status=?
        WHERE bill_id=?
        """;

        try(
                Connection connection=DatabaseConnection.getConnection();
                java.sql.PreparedStatement statement=connection.prepareStatement(sql)
        ){

            statement.setInt(1,billing.getAppointmentId());
            statement.setBigDecimal(2,billing.getAmount());
            statement.setString(3,billing.getPaymentStatus());
            statement.setInt(4,billing.getBillId());

            return statement.executeUpdate()>0;

        }catch(Exception e){
            e.printStackTrace();
        }

        return false;
    }


    @Override
    public boolean deleteBill(int billId) {

        String sql="DELETE FROM billing WHERE bill_id=?";

        try(
                Connection connection=DatabaseConnection.getConnection();
                java.sql.PreparedStatement statement=connection.prepareStatement(sql)
        ){

            statement.setInt(1,billId);

            return statement.executeUpdate()>0;

        }catch(Exception e){
            e.printStackTrace();
        }

        return false;
    }




}