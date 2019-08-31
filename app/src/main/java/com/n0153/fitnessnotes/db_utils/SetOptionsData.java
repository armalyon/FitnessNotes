package com.n0153.fitnessnotes.db_utils;

import java.util.Date;

public class SetOptionsData {

    private Date date;
    private String repsOrTime;
    private String weightOrDist;
    private String note;

    public SetOptionsData(Date date, String repsOrTime, String weightOrDist, String note) {
        this.date = date;
        this.repsOrTime = repsOrTime;
        this.weightOrDist = weightOrDist;
        this.note = note;
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
}
