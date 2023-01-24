package combopt.iv17041.LPD.domain;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.optaplanner.persistence.jackson.impl.domain.solution.JacksonSolutionFileIO;

public class TaskScheduleJsonSolutionFileIO extends JacksonSolutionFileIO<TaskSchedule> {
    public TaskScheduleJsonSolutionFileIO() {
        super(TaskSchedule.class, new ObjectMapper().findAndRegisterModules());
    }
}
