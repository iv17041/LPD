package combopt.iv17041.LPD.domain;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import org.optaplanner.core.api.domain.entity.PlanningEntity;

import org.optaplanner.core.api.domain.lookup.PlanningId;
import org.optaplanner.core.api.domain.variable.IndexShadowVariable;
import org.optaplanner.core.api.domain.variable.InverseRelationShadowVariable;

@PlanningEntity
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class,
        property = "id")
public class Task  {

    @PlanningId
    private Long id;
    private TaskType taskType;
    private int indexInTaskType;
    private Project project;
    private int readyTime;
    private Priority priority;

    // Shadow variables
    @InverseRelationShadowVariable(sourceVariableName = "tasks")
    private Developer developer;
    @IndexShadowVariable(sourceVariableName = "tasks")
    private Integer index;

    private Integer startTime; // In minutes

    public Task() {
    }

    public Task(long id, TaskType taskType, int indexInTaskType, Project customer, int readyTime, Priority priority) {
        this.id = id;
        this.taskType = taskType;
        this.indexInTaskType = indexInTaskType;
        this.project = customer;
        this.readyTime = readyTime;
        this.priority = priority;
    }

    public TaskType getTaskType() {
        return taskType;
    }

    public void setTaskType(TaskType taskType) {
        this.taskType = taskType;
    }

    public int getIndexInTaskType() {
        return indexInTaskType;
    }

    public void setIndexInTaskType(int indexInTaskType) {
        this.indexInTaskType = indexInTaskType;
    }

    public Project getProject() {
        return project;
    }

    public void setProject(Project project) {
        this.project = project;
    }

    public int getReadyTime() {
        return readyTime;
    }

    public void setReadyTime(int readyTime) {
        this.readyTime = readyTime;
    }

    public Priority getPriority() {
        return priority;
    }

    public void setPriority(Priority priority) {
        this.priority = priority;
    }

    public Developer getDeveloper() {
        return developer;
    }

    public void setDeveloper(Developer developer) {
        this.developer = developer;
    }

    public Integer getIndex() {
        return index;
    }

    public void setIndex(Integer index) {
        this.index = index;
    }

    public Integer getStartTime() {
        return startTime;
    }

    public void setStartTime(Integer startTime) {
        this.startTime = startTime;
    }

    // ************************************************************************
    // Complex methods
    // ************************************************************************

    public int getMissingSkillCount() {
        if (developer == null) {
            return 0;
        }
        int count = 0;
        for (Skill skill : taskType.getRequiredSkillList()) {
            if (!developer.getSkillSet().contains(skill)) {
                count++;
            }
        }
        return count;
    }

    /**
     * In minutes
     *
     * @return at least 1 minute
     */
    public int getDuration() {
        Affinity affinity = getAffinity();
        return taskType.getBaseDuration() * affinity.getDurationMultiplier();
    }

    public Affinity getAffinity() {
        return (developer == null) ? Affinity.NONE : developer.getAffinity(project);
    }

    public Integer getEndTime() {
        if (startTime == null) {
            return null;
        }
        return startTime + getDuration();
    }

    public String getCode() {
        return taskType + "-" + indexInTaskType;
    }

    public String getTitle() {
        return taskType.getTitle();
    }

    @Override
    public String toString() {
        return getCode();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}

