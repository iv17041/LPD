package combopt.iv17041.LPD.domain;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import org.optaplanner.core.api.domain.entity.PlanningEntity;
import org.optaplanner.core.api.domain.variable.InverseRelationShadowVariable;

import java.util.ArrayList;
import java.util.List;

@PlanningEntity
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class,
        property = "fullName")
public class Developer {
    private String fullName;

    @InverseRelationShadowVariable(sourceVariableName = "developer")
    private List<Task> taskList;

    public Developer() {
        setTaskList(new ArrayList<>());
    }

    public Developer(String code) {
        setFullName(code);
        setTaskList(new ArrayList<>());
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    @Override
    public String toString() {
        return this.getFullName();
    }

    public List<Task> getTaskList() {
        return taskList;
    }

    public void setTaskList(List<Task> taskList) {
        this.taskList = taskList;
    }

}
