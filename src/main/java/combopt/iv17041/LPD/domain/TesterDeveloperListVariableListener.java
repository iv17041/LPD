package combopt.iv17041.LPD.domain;

import org.optaplanner.core.api.domain.variable.VariableListener;
import org.optaplanner.core.api.score.director.ScoreDirector;

public class TesterDeveloperListVariableListener implements VariableListener<TaskSchedule, Task> {
    @Override
    public void beforeVariableChanged(ScoreDirector<TaskSchedule> scoreDirector, Task task) {
        if (task.getDeveloper() != null) {

            if (scoreDirector.getWorkingSolution().getTaskList().stream()
                    .filter(t -> t.getDeveloper() != null &&
                            t.getTester().equals(task.getTester())
                            && task.getDeveloper().equals(t.getDeveloper()))
            .count() == 1) {
                scoreDirector.beforeVariableChanged(task.getTester(), "developerList");
                task.getTester().getDeveloperList().remove(task.getDeveloper());
                scoreDirector.afterVariableChanged(task.getTester(), "developerList");
            }
        }
    }

    @Override
    public void afterVariableChanged(ScoreDirector<TaskSchedule> scoreDirector, Task task) {
        if (task.getDeveloper() != null) {
            scoreDirector.beforeVariableChanged(task.getTester(), "developerList");
            task.getTester().getDeveloperList().add(task.getDeveloper());
            scoreDirector.afterVariableChanged(task.getTester(), "developerList");
        }

    }

    @Override
    public void beforeEntityAdded(ScoreDirector<TaskSchedule> scoreDirector, Task task) {

    }

    @Override
    public void afterEntityAdded(ScoreDirector<TaskSchedule> scoreDirector, Task task) {

    }

    @Override
    public void beforeEntityRemoved(ScoreDirector<TaskSchedule> scoreDirector, Task task) {

    }

    @Override
    public void afterEntityRemoved(ScoreDirector<TaskSchedule> scoreDirector, Task task) {

    }
}
