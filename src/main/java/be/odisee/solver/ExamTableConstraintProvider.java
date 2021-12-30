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
                studentExamTimeSlotConflict(constraintFactory),
                timeSlotsBetweenExamsConflict(constraintFactory)
        };
    }

    Constraint studentExamTimeSlotConflict(ConstraintFactory constraintFactory) {
        return constraintFactory.forEachUniquePair(Exam.class, Joiners.equal(Exam::getTimeSlot))
                .filter((exam1, exam2) -> {
                    boolean found = false;
                    for (Student student : exam1.getStudentsList()) {
                        if (exam2.getStudentsList().contains(student)) {
                            found = true;
                            break;
                        }
                    }
                    return found; // Student in zelfde examen? true indien in beide examens.
                })
                .penalize("Student timeslot conflict", HardSoftScore.ONE_HARD);
    }

    Constraint timeSlotsBetweenExamsConflict(ConstraintFactory constraintFactory) {
        return constraintFactory.forEachUniquePair(Exam.class)
                .penalize("Timeslots between exams conflict", HardSoftScore.ONE_SOFT, (exam1, exam2) -> {
                    int scoreCount = 0;
                    if (exam1.getTimeSlot().getId() != exam2.getTimeSlot().getId()) { // Beide examens vallen in het zelfde tijdslot
                        for (Student studentExam1 : exam1.getStudentsList()) {
                            if (exam2.getStudentsList().contains(studentExam1)) {
                                int timeslotDiff = Math.abs(exam1.getTimeSlot().getId() - exam2.getTimeSlot().getId());
                                switch (timeslotDiff) {
                                    case 0:
                                        scoreCount = 16;
                                        break;
                                    case 1:
                                        scoreCount = 8;
                                        break;
                                    case 2:
                                        scoreCount = 4;
                                        break;
                                    case 3:
                                        scoreCount = 2;
                                        break;
                                    case 4:
                                        scoreCount = 1;
                                        break;
                                    default:
                                        break;
                                }
                            }
                        }
                    }
                    return scoreCount;
                });
    }
}
