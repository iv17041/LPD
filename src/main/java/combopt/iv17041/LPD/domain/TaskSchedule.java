package combopt.iv17041.LPD.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.optaplanner.core.api.domain.solution.PlanningEntityCollectionProperty;
import org.optaplanner.core.api.domain.solution.PlanningScore;
import org.optaplanner.core.api.domain.solution.PlanningSolution;
import org.optaplanner.core.api.domain.solution.ProblemFactCollectionProperty;
import org.optaplanner.core.api.domain.valuerange.ValueRangeProvider;
import org.optaplanner.core.api.score.buildin.hardsoft.HardSoftScore;
import org.optaplanner.core.api.score.constraint.Indictment;

import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

@PlanningSolution
public class TaskSchedule {
    @PlanningEntityCollectionProperty
    private List<Task> taskList;
    @ValueRangeProvider(id = "developers")
    @PlanningEntityCollectionProperty
    private List<Developer> developerList;
    @ValueRangeProvider(id = "timeslots")
    @ProblemFactCollectionProperty
    private List<TimeSlot> timeSlotList;
    @PlanningEntityCollectionProperty
    private List<Tester> testerList;

    @PlanningScore
    private HardSoftScore score;

    private Long id;

    public TaskSchedule() {

        setTaskList(new LinkedList<>());
        setDeveloperList(new LinkedList<>());
        setTimeSlotList(new LinkedList<>());
        setTesterList(new LinkedList<>());
    }

    @JsonIgnore
    public List<Task> getTasks(Developer developer, TimeSlot timeSlot) {
        return this.getTaskList().stream()
                .filter(task -> task.getDeveloper() != null && task.getTimeSlot() != null &&
                        task.getDeveloper().equals(developer) && task.getTimeSlot().equals(timeSlot))
                .collect(Collectors.toList());

    }

    public List<Task> getTaskList() {
        return taskList;
    }

    public void setTaskList(List<Task> taskList) {
        this.taskList = taskList;
    }

    public List<Developer> getDeveloperList() {
        return developerList;
    }

    public void setDeveloperList(List<Developer> developerList) {
        this.developerList = developerList;
    }

    public List<TimeSlot> getTimeSlotList() {
        return timeSlotList;
    }

    public void setTimeSlotList(List<TimeSlot> timeSlotList) {
        this.timeSlotList = timeSlotList;
    }

    public List<Tester> getTesterList() {
        return testerList;
    }

    public void setTesterList(List<Tester> testerList) {
        this.testerList = testerList;
    }

    public HardSoftScore getScore() {
        return score;
    }

    public void setScore(HardSoftScore score) {
        this.score = score;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
