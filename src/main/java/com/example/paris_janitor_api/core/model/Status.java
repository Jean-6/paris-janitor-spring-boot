package com.example.paris_janitor_api.core.model;

public enum Status {
    PENDING(1,"En attente"),
    IN_PROGRESS(2,"En cours"),
    VALIDATED(3,"Approuvé"),
    REJECTED(4,"Rejeté");

    private int code;
    private String description;

    Status(int code, String description) {}

    public int getCode() {
        return code;
    }

    public String getDescription() {
        return description;
    }

    public Status fromCode(int code){
        for(Status status : values()) if(status.getCode() == code) return status;
        throw new IllegalArgumentException("Code inconnu : "+code);
    }

}
