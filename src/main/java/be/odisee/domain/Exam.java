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
    private TimeSlot timeSlot;

    private List<Student> studentsList;

    public Exam() {
    }

    public Exam(int id) {
        this.id = id;
        this.studentsList = new LinkedList<Student>();
    }

    public int getId() {
        return id;
    }

    public TimeSlot getTimeSlot() {
        return timeSlot;
    }

    public void setTimeslot(TimeSlot timeSlot) {
        this.timeSlot = timeSlot;
    }

    public List<Student> getStudentsList() {
        return studentsList;
    }

    public void addStudent(Student student) {
        studentsList.add(student);
    }

    public void setStudentsList(List<Student> studentsList) {
        this.studentsList = studentsList;
    }

    @Override
    public String toString() {
        StringBuilder resultString = new StringBuilder("Examen nr: " + String.format("%-4s", this.getId()) + " Studenten: ");
        int count = 0;
        for (Student student : this.getStudentsList()) {
            count++;
            resultString.append(student.getId());
            if (count != this.getStudentsList().size()) {
                resultString.append(", ");
            }
        }
        return resultString.toString();
    }
}
