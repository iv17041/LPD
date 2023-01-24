package combopt.iv17041.LPD.domain;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import org.optaplanner.core.api.domain.entity.PlanningEntity;
import org.optaplanner.core.api.domain.variable.ShadowVariable;

import java.util.HashSet;
import java.util.Set;

@PlanningEntity
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class,
        property = "name")
public class Tester {
    private String name;

    @ShadowVariable(sourceEntityClass = Task.class, sourceVariableName = "developer", variableListenerClass = TesterDeveloperListVariableListener.class)
    private Set<Developer> developerList;

    public Tester() {
        setDeveloperList(new HashSet<>());
    }

    public Tester(String name) {
        setName(name);
        setDeveloperList(new HashSet<>());
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<Developer> getDeveloperList() {
        return developerList;
    }

    public void setDeveloperList(Set<Developer> developerList) {
        this.developerList = developerList;
    }

    @Override
    public String toString() {
        return this.getName();
    }
}
