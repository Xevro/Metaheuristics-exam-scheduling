/*
 * Copyright 2021 Red Hat, Inc. and/or its affiliates.
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

package be.odisee;

import java.time.Duration;
import java.util.*;

import be.odisee.data.ExamDataReader;
import be.odisee.domain.*;
import be.odisee.solver.ExamTableConstraintProvider;
import org.optaplanner.core.api.solver.Solver;
import org.optaplanner.core.api.solver.SolverFactory;
import org.optaplanner.core.config.solver.SolverConfig;

public class ExamTableApp {

    public static void main(String[] args) {
        SolverFactory<ExamTableSolution> solverFactory = SolverFactory.create(new SolverConfig()
                .withSolutionClass(ExamTableSolution.class)
                .withEntityClasses(Exam.class)
                .withConstraintProviderClass(ExamTableConstraintProvider.class)
                .withTerminationSpentLimit(Duration.ofSeconds(5)));

        ExamTableSolution problem = getExamReaderData();
        Solver<ExamTableSolution> solver = solverFactory.buildSolver();
        ExamTableSolution solution = solver.solve(problem);

        printExamTable(solution);
    }

    public static ExamTableSolution getExamReaderData() {
        ExamDataReader dataReader = new ExamDataReader("benchmarks/lse-f-91.crs", "benchmarks/lse-f-91.stu");
        List<Exam> exams = dataReader.getExams();
        List<TimeSlot> timeslots = dataReader.getTimeslots();

        return new ExamTableSolution(timeslots, exams);
    }

    private static void printExamTable(ExamTableSolution examTable) {
        for (TimeSlot timeSlot : examTable.getTimeslotList()) {
            System.out.println("------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------");
            System.out.println(timeSlot);
            for (Exam exam : examTable.getExamList()) {
                if (exam.getTimeslot().getId() == timeSlot.getId()) {
                    System.out.println(exam);
                }
            }
        }
        System.out.println("\nScore:" + examTable.getScore());
    }
}
