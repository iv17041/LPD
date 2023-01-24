package combopt.iv17041.LPD.score;

import combopt.iv17041.LPD.domain.Task;
import combopt.iv17041.LPD.domain.Developer;
import combopt.iv17041.LPD.domain.Tester;
import org.optaplanner.core.api.score.buildin.bendable.BendableScore;
import org.optaplanner.core.api.score.buildin.hardsoft.HardSoftScore;
import org.optaplanner.core.api.score.stream.Constraint;
import org.optaplanner.core.api.score.stream.ConstraintFactory;
import org.optaplanner.core.api.score.stream.ConstraintProvider;

import java.util.function.Function;
import java.util.stream.Collectors;

import static org.optaplanner.core.api.score.stream.Joiners.equal;

public class TaskSchedulerConstraintProvider implements ConstraintProvider {
    @Override
    public Constraint[] defineConstraints(ConstraintFactory constraintFactory) {

        return new Constraint[] {
                //Hard
                sameDeveloper(constraintFactory),
                sameTester(constraintFactory),
                //Soft
                sameDeveloperTester(constraintFactory),
                sameDayTester(constraintFactory)
        };
    }

    private Constraint sameDeveloper(ConstraintFactory constraintFactory) {
        return constraintFactory
                .forEachUniquePair(Task.class, equal(Task::getDeveloper), equal(Task::getTimeSlot))
                .penalize(HardSoftScore.ONE_HARD)
                .asConstraint("same Developer");
    }

    private Constraint sameTester(ConstraintFactory constraintFactory) {
        return constraintFactory
                .forEachUniquePair(Task.class, equal(Task::getTester), equal(Task::getTimeSlot))
                .penalize(HardSoftScore.ONE_HARD)
                .asConstraint("same Tester");
    }

    /*private Constraint minimizeMakespan(ConstraintFactory constraintFactory) {
        return constraintFactory.forEach(Employee.class)
                .penalize(BendableScore.ofSoft(BENDABLE_SCORE_HARD_LEVELS_SIZE, BENDABLE_SCORE_SOFT_LEVELS_SIZE, 1, 1),
                        employee -> employee.getEndTime() * employee.getEndTime())
                .asConstraint("Minimize makespan, latest ending employee first");
    }*/


    private Constraint sameDeveloperTester(ConstraintFactory constraintFactory) {
        return constraintFactory
                .forEach(Tester.class)
                .filter(tester -> tester.getDeveloperList().size() > 1)
                .penalize(HardSoftScore.ONE_SOFT)
                .asConstraint("same Developer for Tester");
    }

    private Constraint sameDayTester(ConstraintFactory constraintFactory) {
        return constraintFactory
                .forEachUniquePair(Task.class)
                .filter((l1,l2)->!l1.getTimeSlot().getDayOfWeek().equals(l2.getTimeSlot().getDayOfWeek()))
                .penalize(HardSoftScore.ONE_SOFT)
                .asConstraint("same Day for Tester");
    }
}
