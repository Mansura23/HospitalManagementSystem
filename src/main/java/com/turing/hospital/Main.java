package com.turing.hospital;

import com.turing.hospital.manager.HospitalManager;
import com.turing.hospital.model.Doctor;
import com.turing.hospital.model.Patient;
import com.turing.hospital.model.Room;
import com.turing.hospital.model.Specialization;

import java.io.FileNotFoundException;
import java.time.LocalDate;

public class Main {
    public static void main(String[] args) throws FileNotFoundException {
        String filePath = "data/patient.txt";
        LocalDate birthDate = LocalDate.of(1990, 1, 1);
        Patient patient1=new Patient("1","Mansura",birthDate,"woman","heartDasie");
        LocalDate birthDate2 = LocalDate.of(1992, 3, 9);
        Patient patient2=new Patient("2","Nermin",birthDate2,"woman","stomachAche");
        HospitalManager hospitalManager=new HospitalManager();
        hospitalManager.addPatient(patient1);
        hospitalManager.addPatient(patient2);
        HospitalManager.savePatientsToFile(filePath);
        hospitalManager.listAllPatients();
        LocalDate birthTime = LocalDate.of(2000,7,5);
        LocalDate employeeTime = LocalDate.of(2025,7,6);
        Doctor doctor1=new Doctor("3","Arif",birthTime,"Man", Specialization.CARDIOLOGIST,employeeTime);
        LocalDate birthTime2 = LocalDate.of(2001,4,6);
        LocalDate employeeTime2 = LocalDate.of(2025,8,6);
        Doctor doctor2=new Doctor("4","Leyla",birthTime2,"woman",Specialization.GENERAL_PRACTITIONER,employeeTime2);
        String filePath2="data/doctor.txt";

        HospitalManager.saveDoctorsToFile(filePath2);
        HospitalManager.loadDoctorsFromFile(filePath2);
        hospitalManager.addDoctor(doctor1);
        hospitalManager.addDoctor(doctor2);
        hospitalManager.listAllDoctors();
        Room room=new Room(23);
        room.assignPatient(patient1);
        Room room2=new Room(24);
        room.assignPatient(patient2);
        hospitalManager.assignRoomToPatient(23,patient1);















    }
}
