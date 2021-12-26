
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

    // Needed for OptaPlanner planning clone
    public Exam() {
    }

    public Exam(int id) {
        this.id = id;
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
        return "Examen nr: " +
                String.format("%-4s", this.getId()) + " " +
                "Studenten: " + this.getStudents().stream().map(student -> "" +
                student.getId()).collect(Collectors.joining(", "));
    }

    @Override
    public int compareTo(Exam exam) {
        return (this.getId() == exam.getId()) ? 0 : 1;
    }
}
