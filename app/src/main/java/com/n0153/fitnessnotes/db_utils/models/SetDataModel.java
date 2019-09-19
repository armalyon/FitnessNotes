package com.n0153.fitnessnotes.db_utils.models;

import java.util.Date;

public class SetDataModel {

    private String setText;
    private Date date;
    private String exerciseName;

    public SetDataModel(String exerciseName, String setText, Date date) {
        this.setText = setText;
        this.date = date;
        this.exerciseName = exerciseName;
    }

    public String getSetText() {
        return setText;
    }

    public Date getDate() {
        return date;
    }

    public String getExerciseName() {
        return exerciseName;
    }
}
