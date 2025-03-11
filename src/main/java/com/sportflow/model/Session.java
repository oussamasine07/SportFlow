package com.sportflow.model;

public class Session {
    private int sessionId;
    private String date;
    private Member member;
    private Trainer trainer;

    public Session () {}

    public int getSessionId() {
        return sessionId;
    }

    public String getDate() {
        return date;
    }

    public Member getMember() {
        return member;
    }

    public Trainer getTrainer() {
        return trainer;
    }

    public void setSessionId(int sessionId) {
        this.sessionId = sessionId;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public void setMember(Member member) {
        this.member = member;
    }

    public void setTrainer(Trainer trainer) {
        this.trainer = trainer;
    }
}
