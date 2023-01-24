package combopt.iv17041.LPD.domain;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import org.optaplanner.core.api.domain.entity.PlanningEntity;
import org.optaplanner.core.api.domain.lookup.PlanningId;
import org.optaplanner.core.api.domain.variable.PlanningVariable;

@PlanningEntity(difficultyComparatorClass = TaskComparator.class)
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class,
        property = "title")
public class Task {
    @PlanningId
    private String title;
    @PlanningVariable(valueRangeProviderRefs = "developers")
    private Developer developer;
    @PlanningVariable(valueRangeProviderRefs = "timeslots")
    private TimeSlot timeSlot;
    private int priority;
    private Tester tester;

    public Task() { }

    public Task(String title, Developer developer, TimeSlot timeSlot, int priority, Tester tester) {
        setPriority(priority);
        setDeveloper(developer);
        setTester(tester);
        setTitle(title);
        setTimeSlot(timeSlot);
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Developer getDeveloper() {
        return developer;
    }

    public void setDeveloper(Developer developer) {
        this.developer = developer;
    }

    public TimeSlot getTimeSlot() {
        return timeSlot;
    }

    public void setTimeSlot(TimeSlot timeSlot) {
        this.timeSlot = timeSlot;
    }

    public int getPriority() {
        return priority;
    }

    public void setPriority(int priority) {
        this.priority = priority;
    }

    public Tester getTester() {
        return tester;
    }

    public void setTester(Tester tester) {
        this.tester = tester;
    }

    @Override
    public String toString() {
        return this.getTitle();
    }
}
