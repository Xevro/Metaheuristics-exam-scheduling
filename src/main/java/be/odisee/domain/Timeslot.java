package be.odisee.domain;

public class TimeSlot {

    private final int id;

    public TimeSlot(int id) {
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
