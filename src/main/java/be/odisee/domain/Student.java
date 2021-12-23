package be.odisee.domain;

import java.util.Set;
import java.util.TreeSet;

public class Student implements Comparable<Student> {

    private final int id;
    private Set<Exam> exams;

    public Student(int id) {
        this.id = id;
        exams = new TreeSet<>();
    }

    public Set<Exam> getExams() {
        return exams;
    }

    public void setExams(Set<Exam> exams) {
        this.exams = exams;
    }

    public int getId() {
        return id;
    }

    @Override
    public String toString() {
        return "Student{" + getId() + "}";
    }

    @Override
    public int compareTo(Student that) {
        return (this.getId() == that.getId()) ? 0 : 1;
    }
}
