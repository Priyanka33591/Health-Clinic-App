package com.clinic.dao;

import com.clinic.dto.VisitHistory;
import java.util.List;

public interface VisitHistoryDAO {

    int insertVisitHistory(VisitHistory visitHistory);

    VisitHistory getVisitHistoryById(int visitId);

    List<VisitHistory> getAllVisitHistory();

    boolean updateVisitHistory(VisitHistory visitHistory);

    boolean deleteVisitHistory(int visitId);



}