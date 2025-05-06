package com.turing.hospital.model;

import java.time.LocalDate;

public class Doctor extends Person {
    private Specialization specialization;
    private LocalDate employmentDate;

    public Doctor(String id, String name, LocalDate birthDate, String gender, Specialization specialization, LocalDate employmentDate) {
        super(id, name, birthDate, gender);
        this.specialization = specialization;
        this.employmentDate = employmentDate;
    }

    @Override
    String getinfo() {
        return toString();
    }

    @Override
    public String toString() {
        return "Doctor{" +
                "specialization=" + specialization +
                ", employmentDate=" + employmentDate +
                ", id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", birthDate=" + birthDate +
                ", gender='" + gender + '\'' +
                '}';
    }
    public static Doctor fromString(String line) {
        String[] parts = line.split("\\|");
        String id = parts[0].split(":")[1].trim();
        String name = parts[1].split(":")[1].trim();
        LocalDate birth = LocalDate.parse(parts[2].split(":")[1].trim());
        String gender = parts[3].split(":")[1].trim();
        Specialization spec = Specialization.valueOf(parts[4].split(":")[1].trim());
        LocalDate emp = LocalDate.parse(parts[5].split(":")[1].trim());


        return new Doctor(id, name, birth, gender, spec, emp);
    }

    public Specialization getSpecialization() {
        return specialization;
    }

    public LocalDate getEmploymentDate() {
        return employmentDate;
    }

}
