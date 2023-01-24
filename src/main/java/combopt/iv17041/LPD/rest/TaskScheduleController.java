package combopt.iv17041.LPD.rest;

import combopt.iv17041.LPD.domain.TaskSchedule;
import combopt.iv17041.LPD.domain.TaskScheduleJsonSolutionFileIO;
import org.optaplanner.core.api.solver.SolverManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.concurrent.ExecutionException;

@Controller
@RequestMapping("/optimizer")
public class TaskScheduleController {

    private TaskScheduleJsonSolutionFileIO fileIO = new TaskScheduleJsonSolutionFileIO();

    @Autowired
    private SolverManager<TaskSchedule, Long> solverManager;

    @GetMapping("/home")
    @ResponseBody
    public String home() {
        return "Hi!";
    }

    @PostMapping("/solve")
    @ResponseBody
    public TaskSchedule solve(@RequestBody TaskSchedule problem) throws ExecutionException, InterruptedException {

        return solverManager.solve(1l, problem).getFinalBestSolution();
    }

}