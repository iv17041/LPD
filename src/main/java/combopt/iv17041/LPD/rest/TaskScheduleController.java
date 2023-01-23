package combopt.iv17041.LPD.rest;

import combopt.iv17041.LPD.domain.TaskSchedule;
import combopt.iv17041.LPD.domain.LectureScheduleJsonSolutionFileIO;
import combopt.iv17041.LPD.domain.Developer;
import org.optaplanner.core.api.score.ScoreManager;
import org.optaplanner.core.api.score.buildin.hardmediumsoft.HardMediumSoftScore;
import org.optaplanner.core.api.solver.SolverManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.io.File;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.concurrent.ExecutionException;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/optimizer")
public class TaskScheduleController {

    private LectureScheduleJsonSolutionFileIO fileIO = new LectureScheduleJsonSolutionFileIO();

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