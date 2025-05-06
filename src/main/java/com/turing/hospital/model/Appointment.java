package com.turing.hospital.model;

import java.time.LocalDateTime;

public class Appointment {
    private String id;
    private Patient patient;
    private Doctor doctor;
    private LocalDateTime dateTime;
    private String note;

    Appointment(String id, Patient patient, Doctor doctor, LocalDateTime dateTime, String note) {
        this.id = id;
        this.patient = patient;
        this.doctor = doctor;
        this.dateTime = dateTime;
        this.note = note;
    }

    @Override
    public String toString() {
        return "Appointment{" +
                "id='" + id + '\'' +
                ", patient=" + patient +
                ", doctor=" + doctor +
                ", dateTime=" + dateTime +
                ", note='" + note + '\'' +
                '}';
    }

    public static Appointment fromString(String line, Patient p, Doctor d){
        String[] parts = line.split("\\|");
        String id = parts[0].split(":")[1].trim();
        LocalDateTime dateTime = LocalDateTime.parse(parts[3].split(":")[1].trim());
        String note = parts[4].split(":")[1].trim();
        return new Appointment(id,p,d,dateTime,note);
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Patient getPatient() {
        return patient;
    }

    public void setPatient(Patient patient) {
        this.patient = patient;
    }

    public Doctor getDoctor() {
        return doctor;
    }

    public void setDoctor(Doctor doctor) {
        this.doctor = doctor;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public void setDateTime(LocalDateTime dateTime) {
        this.dateTime = dateTime;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }
}
