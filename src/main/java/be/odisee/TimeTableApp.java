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
import java.util.stream.Collectors;

import be.odisee.data.DataReader;
import be.odisee.domain.*;
import be.odisee.solver.TimeTableConstraintProvider;
import org.optaplanner.core.api.solver.Solver;
import org.optaplanner.core.api.solver.SolverFactory;
import org.optaplanner.core.config.solver.SolverConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class TimeTableApp {

    private static final Logger LOGGER = LoggerFactory.getLogger(TimeTableApp.class);

    public static void main(String[] args) {
        SolverFactory<ExamTable> solverFactory = SolverFactory.create(new SolverConfig()
                .withSolutionClass(ExamTable.class)
                .withEntityClasses(Exam.class)
                .withConstraintProviderClass(TimeTableConstraintProvider.class)
                // The solver runs only for 5 seconds on this small dataset.
                // It's recommended to run for at least 5 minutes ("5m") otherwise.
                .withTerminationSpentLimit(Duration.ofSeconds(5)));

        // Load the problem
        ExamTable problem = generateDemoData();
        // Solve the problem
        Solver<ExamTable> solver = solverFactory.buildSolver();
        ExamTable solution = solver.solve(problem);


        printExamTable(solution);
    }

    public static ExamTable generateDemoData() {
        DataReader parser = new DataReader("benchmarks/lse-f-91.crs", "benchmarks/lse-f-91.stu");
        List<Exam> exams = parser.getExams();
        List<TimeSlot> timeslots = parser.getTimeslots();

        return new ExamTable(timeslots, exams);
    }

    private static void printExamTable(ExamTable examTable) {
        for (TimeSlot timeSlot : examTable.getTimeslotList()) {
            System.out.println(timeSlot.toString());
            for (Exam e : examTable.getExamList()) {
                if (e.getTimeslot().getId() == timeSlot.getId()) {
                    System.out.println(e);
                }
            }
        }
        System.out.println("Score:" + examTable.getScore());
    }
}
