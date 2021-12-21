package org.acme.schooltimetabling.domain;

import java.io.Serializable;

public class Timeslot implements Serializable {

    private int id; // 1-200

    public Timeslot(int ID) {
        this.id = ID;
    }

    public int getID() {
        return id;
    }

    public void setID(int id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return id + "";
    }
}
