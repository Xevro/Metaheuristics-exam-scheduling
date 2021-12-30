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
    private List<TimeSlot> timeSlotList;

    @PlanningEntityCollectionProperty
    private List<Exam> examList;

    @PlanningScore
    private HardSoftScore score;

    public ExamTableSolution() {
    }

    public ExamTableSolution(List<TimeSlot> timeSlotList, List<Exam> examList) {
        this.timeSlotList = timeSlotList;
        this.examList = examList;
    }

    public List<TimeSlot> getTimeSlotList() {
        return timeSlotList;
    }

    public List<Exam> getExamList() {
        return examList;
    }

    public HardSoftScore getScore() {
        return score;
    }
}
