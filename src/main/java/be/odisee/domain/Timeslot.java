package be.odisee.domain;

public class Timeslot {

    private final int id;

    public Timeslot(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    @Override
    public String toString() {
        return "TimeSlot{" + getId() + "}";
    }
}
