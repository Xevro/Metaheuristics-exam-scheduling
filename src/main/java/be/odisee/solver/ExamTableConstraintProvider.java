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

package be.odisee.solver;

import be.odisee.domain.*;

import org.optaplanner.core.api.score.buildin.hardsoft.HardSoftScore;
import org.optaplanner.core.api.score.stream.Constraint;
import org.optaplanner.core.api.score.stream.ConstraintFactory;
import org.optaplanner.core.api.score.stream.ConstraintProvider;
import org.optaplanner.core.api.score.stream.Joiners;

public class ExamTableConstraintProvider implements ConstraintProvider {

    @Override
    public Constraint[] defineConstraints(ConstraintFactory constraintFactory) {
        return new Constraint[]{
                studentExamTimeslotConflict(constraintFactory)
        };
    }

    Constraint studentExamTimeslotConflict(ConstraintFactory constraintFactory) {
        return constraintFactory.forEachUniquePair(Exam.class,
                        Joiners.equal(Exam::getTimeslot))
                .filter((exam1, exam2) -> {
                    int studentCount = 0;
                    for (Student students : exam1.getStudents()) {
                        if (exam2.getStudents().contains(students)) {
                            studentCount++;
                        }
                    }
                    return (studentCount > 0);
                })
                .penalize("Student timeslot conflict", HardSoftScore.ONE_HARD);
    }
}