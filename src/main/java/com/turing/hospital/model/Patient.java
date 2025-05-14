package com.turing.hospital.model;

import java.time.LocalDate;

public class Patient extends Person{
    private String diagnosis;

    public Patient(String id, String name, LocalDate birthDate, String gender, String diagnosis) {
        super(id, name, birthDate, gender);
        this.diagnosis = diagnosis;
    }

    @Override
    public String toString() {
        return "ID: " + id +
                " | Name: " + name +
                " | Birth: " + birthDate +
                " | Gender: " + gender +
                " | Diagnosis: " + diagnosis;
    }

    @Override
    String getInfo() {
        return toString();
    }

    public String getDiagnosis() {
        return diagnosis;
    }

    public void setDiagnosis(String diagnosis) {
        this.diagnosis = diagnosis;
    }

    public static Patient fromString(String line) {
        String[] parts = line.split("\\|");
        String id = parts[0].split(":")[1].trim();
        String name = parts[1].split(":")[1].trim();
        LocalDate birth = LocalDate.parse(parts[2].split(":")[1].trim());
        String gender = parts[3].split(":")[1].trim();
        String diagnosis = parts[4].split(":")[1].trim();

        return new Patient(id, name, birth, gender, diagnosis);
    }

}
