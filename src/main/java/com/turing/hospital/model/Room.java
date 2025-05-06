package com.turing.hospital.model;

public class Room {
    private int roomNumber;
    private boolean isOccupied;
    private Patient patient;

    public Room(int roomNumber) {
        this.roomNumber = roomNumber;
    }
    public void assignPatient(Patient patient){
        this.patient = patient;
        this.isOccupied = true;
    }
    public  void  clearRoom(){
        this.patient = null;
        this.isOccupied = false;
    }

    @Override
    public String toString() {
        return "Room{" +
                "roomNumber=" + roomNumber +
                ", isOccupied=" + isOccupied +
                ", patient=" + patient +
                '}';
    }

    public static Room fromString(String line, Patient p){
        String[] parts = line.split("\\|");
        int roomNumber = Integer.parseInt(parts[0].split(":")[1].trim());
        boolean isOccupied = parts[1].split(":")[1].trim().equals("1");
        Room room = new Room(roomNumber);
        if(isOccupied && p!=null){
            room.assignPatient(p);
        }
        return room;

    }

    public int getRoomNumber() {
        return roomNumber;
    }

    public void setRoomNumber(int roomNumber) {
        this.roomNumber = roomNumber;
    }

    public boolean isOccupied() {
        return !isOccupied;
    }

    public void setOccupied(boolean occupied) {
        isOccupied = occupied;
    }

    public Patient getPatient() {
        return patient;
    }

    public void setPatient(Patient patient) {
        this.patient = patient;
    }
}
