package com.sportflow.model;

public class Trainer extends User {
    private int trainerId;
    private String specialty;

    public Trainer() {}

    public int getTrainerId() {
        return trainerId;
    }

    public String getSpecialty() {
        return specialty;
    }

    public void setTrainerId(int trainerId) {
        this.trainerId = trainerId;
    }

    public void setSpecialty(String specialty) {
        this.specialty = specialty;
    }
}
