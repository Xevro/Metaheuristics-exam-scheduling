package be.odisee.domain;

import java.util.LinkedList;
import java.util.List;

public class Student {

    private final int id;
    private List<Exam> examsList;

    public Student(int id) {
        this.id = id;
        examsList = new LinkedList<>();
    }

    public List<Exam> getExamsList() {
        return examsList;
    }

    public void setExamsList(List<Exam> examsList) {
        this.examsList = examsList;
    }

    public int getId() {
        return id;
    }

    @Override
    public String toString() {
        return "Student: " + getId() + "";
    }
}
