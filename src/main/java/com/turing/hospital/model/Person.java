package com.turing.hospital.model;

import java.time.LocalDate;

public  abstract class Person {
    protected String id;
    protected String name;
    protected LocalDate birthDate;
    protected String gender;
    Person(String id, String name, LocalDate birthDate, String gender) {
        this.id = id;
        this.name = name;
        this.birthDate = birthDate;
        this.gender = gender;
    }
    abstract String getInfo();

    public String getId(){
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDate getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(LocalDate birthDate) {
        this.birthDate = birthDate;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }
}
