package com.n0153.fitnessnotes.db_utils.models;

import java.util.Date;

public class SetOptionsDataModel {

    private String name;
    private Date date;
    private String repsOrTime;
    private String weightOrDist;
    private String note;
    private String weightDistUnits;

    public SetOptionsDataModel(String name, Date date, String repsOrTime, String weightOrDist, String note, String weightDistUnits) {
        this.name = name;
        this.date = date;
        this.repsOrTime = repsOrTime;
        this.weightOrDist = weightOrDist;
        this.note = note;
        this.weightDistUnits = weightDistUnits;
    }

    public String getName() {
        return name;
    }

    public Date getDate() {
        return date;
    }

    public String getRepsOrTime() {
        return repsOrTime;
    }

    public String getWeightOrDist() {
        return weightOrDist;
    }

    public String getNote() {
        return note;
    }

    public String getWeightDistUnits() {
        return weightDistUnits;
    }

    @Override
    public String toString() {
        return "SetOptionsDataModel{" +
                "date=" + date +
                ", repsOrTime='" + repsOrTime + '\'' +
                ", weightOrDist='" + weightOrDist + '\'' +
                ", note='" + note + '\'' +
                ", weightDistUnits='" + weightDistUnits + '\'' +
                '}';
    }
}
