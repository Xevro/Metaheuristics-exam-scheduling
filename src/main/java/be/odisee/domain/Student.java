package be.odisee.domain;

import java.util.LinkedList;
import java.util.List;

public class Student {

    private final int id;
    private List<Exam> exams;

    public Student(int id) {
        this.id = id;
        exams = new LinkedList<>();
    }

    public List<Exam> getExams() {
        return exams;
    }

    public void setExams(List<Exam> exams) {
        this.exams = exams;
    }

    public int getId() {
        return id;
    }

    @Override
    public String toString() {
        return getId() + "";
    }
}
