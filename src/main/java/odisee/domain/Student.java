package odisee.domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Student implements Serializable {

    private List<Integer> examIds; // Examens lijst

    public Student() {
        examIds = new ArrayList<Integer>();
    }

    public List<Integer> getExamIds() {
        return examIds;
    }

    public void setExamIds(List<Integer> eid) {
        this.examIds = eid;
    }
}
