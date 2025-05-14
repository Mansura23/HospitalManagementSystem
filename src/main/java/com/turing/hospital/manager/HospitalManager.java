package com.turing.hospital.manager;

import com.turing.hospital.model.Appointment;
import com.turing.hospital.model.Doctor;
import com.turing.hospital.model.Patient;
import com.turing.hospital.model.Room;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class HospitalManager {
    public static List<Patient> patients = new ArrayList<>();
    public static List<Doctor> doctors = new ArrayList<>();
    public static List<Appointment> appointments = new ArrayList<>();
    public static List<Room> rooms = new ArrayList<>();
    String filePath="data/patient.txt";
    String filePath2="data/doctors.txt";
    String filePath3="data/Appointment.txt";
    String filePath4 ="data/Room.txt";
     public  void addPatient(Patient patient){
         if(!patients.contains(patient)){
             patients.add(patient);
         }
     }
     public  void listAllPatients(){
         patients.forEach(System.out::println);

     }
     public static void savePatientsToFile(String filePath) {
         File dir = new File("data");
         if (!dir.exists()) {
             dir.mkdirs();
         }
         try(BufferedWriter writer =new BufferedWriter(new FileWriter(filePath))) {
             for (Patient patient : patients) {
                 writer.write(patient.toString());
                 writer.newLine();
             }
             System.out.println("Patient saved to "+filePath);
         }catch (IOException e){
             System.out.println("Error saving patient to file"+e.getMessage());
         }

     }
     public  static void loadPatientsFromFile(String filePath) throws FileNotFoundException {
        patients.clear();
        try {
            BufferedReader reader = new BufferedReader(new FileReader(filePath));
            String line;
            while ((line=reader.readLine())!=null){
                Patient patient=Patient.fromString(line);
                patients.add(patient);
            }
            System.out.println("Patients loaded from " + filePath);
        } catch (IOException e) {
            System.out.println("Error loading patient from "+filePath+e.getMessage());
        }
     }
     public void addDoctor(Doctor doctor){
         if(!doctors.contains(doctor)){
             doctors.add(doctor);
         }
     }
     public  void listAllDoctors(){
         doctors.forEach(System.out::println);
     }
     public  static void saveDoctorsToFile(String filePath2) {
         File dir = new File("data");
         if (!dir.exists()) {
             dir.mkdirs();
         }
         try {BufferedWriter writer=new BufferedWriter(new FileWriter(filePath2));
             for (Doctor doctor : doctors) {
                 writer.write(doctor.toString());
                 writer.newLine();
             }
         } catch (IOException e) {
             System.out.println("Error saving doctor to file"+e.getMessage());
         }
     }
     public  static void loadDoctorsFromFile(String filePath2) {
         doctors.clear();
         try {
             BufferedReader reader=new BufferedReader(new FileReader(filePath2));
             String line;
             while ((line=reader.readLine())!=null){
                 Doctor doctor=Doctor.fromString(line);
                 doctors.add(doctor);
             }
         } catch (IOException e) {
             System.out.println("Error loading doctor from "+filePath2+e.getMessage());
         }
     }
     public void addAppointment(Appointment appointment){
         appointments.add(appointment);
     }
     public  void listAllAppointments(){
         appointments.forEach(System.out::println);
     }
     public  void saveAppointmentsToFile(String filePath3) {
         File dir = new File("data");
         if (!dir.exists()) {
             dir.mkdirs();
         }
         try {BufferedWriter writer=new BufferedWriter(new FileWriter(filePath3));
             for (Appointment appointment : appointments) {
                 writer.write(appointment.toString());
                 writer.newLine();
             }

         } catch (IOException e) {
             System.out.println("Error saving appointment to file"+e.getMessage());
         }
     }
     public  void loadAppointmentsFromFile(String filePath3) throws IOException {
         appointments.clear();
         try {
             BufferedReader reader=new BufferedReader(new FileReader(filePath3));
             String line;
             while ((line=reader.readLine())!=null){
                 String[] parts = line.split(" \\| ");
                 String patientId = parts[1].split(": ")[1];
                 String doctorId = parts[2].split(": ")[1];
                 Patient patient=patients.stream()
                         .filter(p ->p.getId().equals(patientId))
                         .findFirst().orElse(null);
                 Doctor doctor=doctors.stream()
                         .filter(d ->d.getId().equals(doctorId))
                         .findFirst().orElse(null);
                 if (patient != null && doctor != null) {
                     Appointment appointment=Appointment.fromString(line,patient,doctor);
                     appointments.add(appointment);
                 }
                 else {
                     System.out.println("Skipping appointment - Patient/Doctor not found: " + line);
                 }

             }
             System.out.println("Appointments loaded from " + filePath3);
         } catch (IOException e) {
             System.out.println("Error loading appointment from "+filePath3+e.getMessage());
         }


     }
     public void addRoom(int roomNumber){
         for (Room room : rooms) {
             if (room.getRoomNumber() == roomNumber) {
                 throw new  IllegalArgumentException("Room already exists!");
             }

         }
         rooms.add(new Room(roomNumber));

     }
     public  void listAvailableRooms(){
         for (Room room : rooms) {
             if(room.isOccupied()){
                 System.out.println(room.toString());
             }
         }

     }
     public void assignRoomToPatient(int roomNumber, Patient p){
         for (Room room : rooms) {
             if (room.getRoomNumber() == roomNumber) {
                 if(room.isOccupied()){
                     room.assignPatient(p);
                     System.out.println("Patient"+p.getId()+"assigned to room "+room.getRoomNumber());
                 }
                 else {
                     throw new  IllegalArgumentException("Room already exists!");
                 }
             }

         }
     }
     public static void saveRoomsToFile(String filePath4){
         try(BufferedWriter br=new BufferedWriter(new FileWriter(filePath4))) {
             for (Room room : rooms) {
                 br.write(room.toString());
                 br.newLine();
             }
             System.out.println("Rooms saved to "+filePath4);
         } catch (IOException e) {
             System.out.println("Error saving rooms to file"+e.getMessage());
         }
     }
     public  void loadRoomsFromFile(String filePath4){
         try(BufferedReader reader=new BufferedReader(new FileReader(filePath4))) {
             String line;
             rooms.clear();
             while ((line=reader.readLine())!=null){
                 if(!line.trim().isEmpty()){
                     try {
                         Room room= Room.fromString(line, (Patient) patients);
                         rooms.add(room);
                     } catch (IllegalArgumentException e){
                         System.out.println("Error parsing room data: " + line + " - " + e.getMessage());
                     }
                 }
                 System.out.println(line);
             }
         } catch (IOException e) {
             System.out.println("Error loading rooms from "+filePath4+e.getMessage());
         }
     }
}
