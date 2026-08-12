package com.clinic.dto;

import java.sql.Timestamp;

public class VisitHistory {

    private int visitId;
    private int appointmentId;
    private String diagnosis;
    private String prescription;
    private Timestamp createdOn;

    @Override
    public String toString() {
        return "VisitHistory{" +
                "visitId=" + visitId +
                ", appointmentId=" + appointmentId +
                ", diagnosis='" + diagnosis + '\'' +
                ", prescription='" + prescription + '\'' +
                ", createdOn=" + createdOn +
                ", visitNotes='" + visitNotes + '\'' +
                '}';
    }

    private String visitNotes;

    public VisitHistory() {
    }

    public VisitHistory(int appointmentId, String diagnosis, String prescription) {
        this.appointmentId = appointmentId;
        this.diagnosis = diagnosis;
        this.prescription = prescription;
    }

    public int getVisitId() {
        return visitId;
    }

    public void setVisitId(int visitId) {
        this.visitId = visitId;
    }

    public int getAppointmentId() {
        return appointmentId;
    }

    public void setAppointmentId(int appointmentId) {
        this.appointmentId = appointmentId;
    }

    public String getDiagnosis() {
        return diagnosis;
    }

    public void setDiagnosis(String diagnosis) {
        this.diagnosis = diagnosis;
    }

    public String getPrescription() {
        return prescription;
    }

    public void setPrescription(String prescription) {
        this.prescription = prescription;
    }

    public Timestamp getCreatedOn() {
        return createdOn;
    }

    public void setCreatedOn(Timestamp createdOn) {
        this.createdOn = createdOn;
    }

    public String getVisitNotes() {
        return visitNotes;
    }
    public void setVisitNotes(String visitNotes) {
        this.visitNotes = visitNotes;
    }


}