package com.clinic.dao;

import com.clinic.dto.Billing;
import java.util.List;

public interface BillingDAO {

    int insertBill(Billing billing);

    Billing getBillById(int billId);

    List<Billing> getAllBills();

    boolean updateBill(Billing billing);

    boolean deleteBill(int billId);

}