package combopt.iv17041.LPD.domain;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import org.optaplanner.core.api.domain.entity.PlanningEntity;
import org.optaplanner.core.api.domain.lookup.PlanningId;
import org.optaplanner.core.api.domain.variable.PlanningListVariable;

import java.util.*;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Set;


@PlanningEntity
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class,
        property = "fullName")
public class Developer {
    @PlanningId
    private String fullName;

    private Set<Skill> skillSet;
    private Map<Project, Affinity> affinityMap;

    @PlanningListVariable(valueRangeProviderRefs = "taskRange")
    private List<Task> tasks;

    public Developer() {
    }

    public Developer(String fullName) {
        this.fullName = fullName;
        skillSet = new LinkedHashSet<>();
        affinityMap = new LinkedHashMap<>();
        tasks = new ArrayList<>();
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public Set<Skill> getSkillSet() {
        return skillSet;
    }

    public void setSkillSet(Set<Skill> skillSet) {
        this.skillSet = skillSet;
    }

    public Map<Project, Affinity> getAffinityMap() {
        return affinityMap;
    }

    public void setAffinityMap(Map<Project, Affinity> affinityMap) {
        this.affinityMap = affinityMap;
    }

    public List<Task> getTasks() {
        return tasks;
    }

    public void setTasks(List<Task> tasks) {
        this.tasks = tasks;
    }

    // ************************************************************************
    // Complex methods
    // ************************************************************************

    /**
     * @param customer never null
     * @return never null
     */
    public Affinity getAffinity(Project customer) {
        Affinity affinity = affinityMap.get(customer);
        if (affinity == null) {
            affinity = Affinity.NONE;
        }
        return affinity;
    }

    public Integer getEndTime() {
        return tasks.isEmpty() ? 0 : tasks.get(tasks.size() - 1).getEndTime();
    }


    @Override
    public String toString() {
        return fullName;
    }
}
