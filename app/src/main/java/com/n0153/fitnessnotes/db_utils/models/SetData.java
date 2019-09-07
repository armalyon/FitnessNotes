package com.n0153.fitnessnotes.db_utils.models;

import java.util.Date;

public class SetData {

    private String setText;
    private Date date;

    public SetData(String setText, Date date) {
        this.setText = setText;
        this.date = date;
    }

    public String getSetText() {
        return setText;
    }

    public Date getDate() {
        return date;
    }
}
