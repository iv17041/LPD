package combopt.iv17041.LPD.rest;

import combopt.iv17041.LPD.domain.TaskSchedule;
import combopt.iv17041.LPD.domain.TaskScheduleJsonSolutionFileIO;
import org.optaplanner.core.api.solver.SolverManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.io.File;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ExecutionException;

@Controller
@RequestMapping("/optimizer")
public class TaskSchedController {

    private TaskScheduleJsonSolutionFileIO fileIO = new TaskScheduleJsonSolutionFileIO();

    @Autowired
    private SolverManager<TaskSchedule, Long> solverManager;

    private Map<Long, TaskSchedule> scheduleList = new HashMap<Long, TaskSchedule>();

    @GetMapping("/home")
    @ResponseBody
    public String home() {
        return "Hi!";
    }

    @GetMapping("/solve")
    @ResponseBody
    public TaskSchedule solve3() throws ExecutionException, InterruptedException {
        TaskSchedule problem = fileIO.read(new File("data/problem.json"));
        return solverManager.solve(1l, problem).getFinalBestSolution();
    }

    @PostMapping("/solve2")
    @ResponseBody
    public TaskSchedule solve(@RequestBody TaskSchedule problem) throws ExecutionException, InterruptedException {
        return solverManager.solve(1l, problem).getFinalBestSolution();
    }

    @PostMapping("/solve3")
    @ResponseBody
    public void solve2(@RequestBody TaskSchedule problem) {
        solverManager.solveAndListen(problem.getId(), id -> problem, solution -> scheduleList.put(solution.getId(), solution));
    }

    @GetMapping("/get")
    @ResponseBody
    public TaskSchedule getSolution(@RequestParam(name="id") Long id) {
        return scheduleList.get(id);
    }

}
