package org.acme.schooltimetabling.domain;

import org.optaplanner.core.api.domain.entity.PlanningEntity;
import org.optaplanner.core.api.domain.variable.PlanningVariable;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@PlanningEntity
public class Student implements Serializable {

    @PlanningVariable(valueRangeProviderRefs = "examRange")
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