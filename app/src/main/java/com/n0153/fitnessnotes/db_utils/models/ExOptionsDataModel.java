package com.n0153.fitnessnotes.db_utils.models;

public class ExOptionsDataModel {

    private String units;
    private String type;

    public ExOptionsDataModel(String type, String units) {
        this.units = units;
        this.type = type;
    }

    public String getUnits() {
        return units;
    }


    public String getType() {
        return type;
    }

}
