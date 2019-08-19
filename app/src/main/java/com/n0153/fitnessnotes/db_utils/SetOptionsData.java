package com.n0153.fitnessnotes.db_utils;

import java.util.Date;

public class SetOptionsData {

    private Date date;
    private String repsOrTime;
    private String weightOrDist;

    public SetOptionsData(Date date, String repsOrTime, String weightOrDist) {
        this.date = date;
        this.repsOrTime = repsOrTime;
        this.weightOrDist = weightOrDist;
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
}
