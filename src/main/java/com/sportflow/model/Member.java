package com.sportflow.model;

public class Member extends User {
    private int memberId;
    private String dateOfBirth;
    private String practicedSport;

    public Member () {}

    public int getMemberId() {
        return memberId;
    }

    public String getDateOfBirth () { return dateOfBirth; }

    public String getPracticedSport() {
        return practicedSport;
    }

    public void setMemberId(int memberId) {
        this.memberId = memberId;
    }

    public void setDateOfBirth (String dateOfBirth ) { this.dateOfBirth = dateOfBirth; }

    public void setPracticedSport(String practicedSport) {
        this.practicedSport = practicedSport;
    }
}
