package com.n0153.fitnessnotes.db_utils;

import java.util.Date;

public class SetOptionsData {

    private Date date;
    private String repsOrTime;
    private String weightOrDist;
    private String note;
    private String weightDistUnits;

    public SetOptionsData(Date date, String repsOrTime, String weightOrDist, String note, String weightDistUnits) {
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
