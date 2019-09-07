package com.n0153.fitnessnotes.db_utils.models;

public class ExOptionsData {

    private String units;
    private String type;

    public ExOptionsData(String type, String units) {
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
