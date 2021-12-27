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

package be.odisee.domain;

import java.util.List;

import org.optaplanner.core.api.domain.solution.PlanningEntityCollectionProperty;
import org.optaplanner.core.api.domain.solution.PlanningScore;
import org.optaplanner.core.api.domain.solution.PlanningSolution;
import org.optaplanner.core.api.domain.solution.ProblemFactCollectionProperty;
import org.optaplanner.core.api.domain.valuerange.ValueRangeProvider;
import org.optaplanner.core.api.score.buildin.hardsoft.HardSoftScore;

@PlanningSolution
public class ExamTableSolution {
    @ProblemFactCollectionProperty
    @ValueRangeProvider(id = "timeSlotRange")
    private List<TimeSlot> timeslotList;

    @PlanningEntityCollectionProperty
    private List<Exam> examList;

    @PlanningScore
    private HardSoftScore score;

    public ExamTableSolution() {
    }

    public ExamTableSolution(List<TimeSlot> timeslotList, List<Exam> examList) {
        this.timeslotList = timeslotList;
        this.examList = examList;
    }

    public List<TimeSlot> getTimeslotList() {
        return timeslotList;
    }

    public List<Exam> getExamList() {
        return examList;
    }

    public HardSoftScore getScore() {
        return score;
    }
}
