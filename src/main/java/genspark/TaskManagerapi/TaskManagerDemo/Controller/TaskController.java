package genspark.TaskManagerapi.TaskManagerDemo.Controller;

import ch.qos.logback.classic.encoder.JsonEncoder;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import genspark.TaskManagerapi.TaskManagerDemo.Entity.Task;
import genspark.TaskManagerapi.TaskManagerDemo.Services.TaskService;
import org.hibernate.event.spi.RefreshEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.codec.json.Jackson2JsonEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Create endpoints and routing.
 */

@RestController
public class TaskController {

    @Autowired
    public TaskService ts;

    @GetMapping("/")
    public String home()
    {
        return "<h1>Welcome to my Task Manager App!</h1><a href=\"/tasks\">View your tasks!</a>";
    }

    @GetMapping("/tasks")
    public String getTasks()
    {
        StringBuilder allHTML = new StringBuilder();
        allHTML.append("<h1>All your tasks!</h1>");
        List<Task> allTasks = this.ts.getAllTasks();

        for(Task t: allTasks) {
            allHTML.append("<br>");
            allHTML.append(String.format("<h2>Task %d:</h2>",t.getTaskID()));
            allHTML.append(String.format("<p>Title: %s</p>", t.getTitle()));
            allHTML.append(String.format("<p>Body: %s</p>", t.getDescription()));
            allHTML.append(String.format("<p>Due Date: %s</p>",
                    (t.getDueDate() == null) ? ("No due date assigned.")
                            : (t.getDueDate().toString())));
            allHTML.append(String.format("<h4>Task %d JSON:</h4>", t.getTaskID()));
            try{
                allHTML.append(String.format("<p>%s</p>", new ObjectMapper().writeValueAsString(t)));
            } catch(JsonProcessingException ex) {
                System.out.println("Json Conversion Failed: "+ex.getMessage());
            }

            allHTML.append(String.format("<a href=\"tasks/%d\">View raw JSON</a>", t.getTaskID()));

            allHTML.append("<br>");

        }

        if(allTasks.isEmpty()) {
            allHTML.append("<h4>No tasks saved currently. Try making some via postman!</h4>");
        }

        return allHTML.toString();
    }

    @GetMapping("/tasks/{taskID}")
    public Task getSingleTask(@PathVariable Long taskID)
    {
        return this.ts.getTaskByID(taskID);
    }

    @PostMapping("/tasks")
    public Task addTask(@RequestBody Task task)
    {
        return this.ts.addNewTask(task);
    }

    @PutMapping("/tasks")
    public Task updateTask(@RequestBody Task task)
    {
        return this.ts.updateTask(task);
    }

    @DeleteMapping("/tasks/{taskID}")
    public String deleteTask(@PathVariable Long taskID)
    {
        return this.ts.deleteTaskByID(taskID);
    }
}
