
package be.odisee.domain;

import org.optaplanner.core.api.domain.entity.PlanningEntity;
import org.optaplanner.core.api.domain.lookup.PlanningId;
import org.optaplanner.core.api.domain.variable.PlanningVariable;

import java.util.LinkedList;
import java.util.List;

@PlanningEntity
public class Exam {

    @PlanningId
    private int id;

    @PlanningVariable(valueRangeProviderRefs = "timeSlotRange")
    private TimeSlot timeslot;

    private List<Student> students;

    // Needed for OptaPlanner planning clone
    public Exam() {
    }

    public Exam(int id) {
        this.id = id;
        this.students = new LinkedList<>();
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

    public List<Student> getStudents() {
        return students;
    }

    public void addStudent(Student studentID) {
        students.add(studentID);
    }

    public void setStudents(List<Student> students) {
        this.students = students;
    }

    @Override
    public String toString() {
        StringBuilder resultString = new StringBuilder("Examen nr: " + String.format("%-4s", this.getId()) + " Studenten: ");
        int count = 0;
        for (Student student : this.getStudents()) {
            count++;
            resultString.append(student.getId());
            if (count != this.getStudents().size()) {
                resultString.append(", ");
            }
        }
        return resultString.toString();
    }
}
