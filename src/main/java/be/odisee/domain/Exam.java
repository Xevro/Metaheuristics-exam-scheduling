
package be.odisee.domain;

import org.optaplanner.core.api.domain.entity.PlanningEntity;
import org.optaplanner.core.api.domain.lookup.PlanningId;
import org.optaplanner.core.api.domain.variable.PlanningVariable;

import java.util.Set;
import java.util.TreeSet;
import java.util.stream.Collectors;

@PlanningEntity
public class Exam implements Comparable<Exam> {

    @PlanningId
    private int id;

    @PlanningVariable(valueRangeProviderRefs = "timeslotRange")
    private TimeSlot timeslot;

    private Set<Student> students;
    private int capacity;


    // Needed for OptaPlanner planning clone
    public Exam() {
    }

    public Exam(int id, int capacity) {
        this.id = id;
        this.capacity = capacity;
        this.students = new TreeSet<>();
    }


    public int getId() {
        return id;
    }

    public TimeSlot getTimeslot() {
        return timeslot;
    }

    public void setTimeslot(TimeSlot timeslot) {
        this.timeslot = timeslot;
    }

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public Set<Student> getStudents() {
        return students;
    }

    public void addSID(Student studentID) {
        students.add(studentID);
    }

    public void setStudents(Set<Student> students) {
        this.students = students;
    }

    @Override
    public String toString() {
        return "Exam{" + String.format("%-4s", getId() + ",") + " [" + getStudents().stream().map(s -> "" + s.getId()).collect(Collectors.joining(",")) + "]}";
    }

    @Override
    public int compareTo(Exam that) {
        return (this.getId() == that.getId()) ? 0 : 1;
    }
}
