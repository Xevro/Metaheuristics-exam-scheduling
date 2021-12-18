/*
 * Copyright 2020 Red Hat, Inc. and/or its affiliates.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.acme.schooltimetabling.domain;

import java.util.List;

import org.optaplanner.core.api.domain.solution.PlanningEntityCollectionProperty;
import org.optaplanner.core.api.domain.solution.PlanningScore;
import org.optaplanner.core.api.domain.solution.PlanningSolution;
import org.optaplanner.core.api.domain.solution.ProblemFactCollectionProperty;
import org.optaplanner.core.api.domain.valuerange.ValueRangeProvider;
import org.optaplanner.core.api.score.buildin.hardsoft.HardSoftScore;

@PlanningSolution
public class TimeTable {

    @PlanningEntityCollectionProperty
    @ValueRangeProvider(id = "timeslotRange")
    private List<Timeslot> timeslotList;

    //@ProblemFactCollectionProperty
    //@ValueRangeProvider(id = "roomRange")
    //private List<Room> roomList;

    @ProblemFactCollectionProperty
    @ValueRangeProvider(id = "examsRange")
    private List<Exam> examList;

    private List<Student> studentList;

    @PlanningScore
    private HardSoftScore score;

    // No-arg constructor required for OptaPlanner
    public TimeTable() {
    }

    public TimeTable(List<Timeslot> timeslotList, List<Exam> examList, List<Student> studentList) {
        this.timeslotList = timeslotList;
        this.examList = examList;
        this.studentList = studentList;
    }

    public List<Timeslot> getTimeslotList() {
        return timeslotList;
    }

    public List<Exam> getExamList() {
        return examList;
    }

    public List<Student> getStudentList() {
        return studentList;
    }

    public HardSoftScore getScore() {
        return score;
    }

}
