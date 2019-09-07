package com.n0153.fitnessnotes.db_utils.models;

import java.util.Date;

public class SetOptionsDataModel {

    private Date date;
    private String repsOrTime;
    private String weightOrDist;
    private String note;
    private String weightDistUnits;

    public SetOptionsDataModel(Date date, String repsOrTime, String weightOrDist, String note, String weightDistUnits) {
        this.date = date;
        this.repsOrTime = repsOrTime;
        this.weightOrDist = weightOrDist;
        this.note = note;
        this.weightDistUnits = weightDistUnits;
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
}
