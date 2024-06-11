package genspark.TaskManagerapi.TaskManagerDemo.Services;

import genspark.TaskManagerapi.TaskManagerDemo.Entity.Task;
import genspark.TaskManagerapi.TaskManagerDemo.Repository.TaskDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.util.List;
import java.util.Optional;

@Service
public class TaskServiceImpl implements TaskService {

    @Autowired
    public TaskDAO taskDAO;

    @Override
    public List<Task> getAllTasks() {
        return this.taskDAO.findAll();
    }

    @Override
    public Task getTaskByID(long taskID) {

        Optional<Task> t = this.taskDAO.findById(taskID);
        Task task = null;
        if(t.isPresent())
            task=t.get();
        else
            throw new RuntimeException("Task Not Found: "+taskID);

        return task;
    }

    @Override
    public Task addNewTask(Task newTask) {
        return this.taskDAO.save(newTask);
    }

    @Override
    public Task updateTask(Task task) {

//        Task t = null;
//        long taskID = task.getTaskID();
//        String taskTitle = task.getTitle();
//        String taskDescription = task.getDescription();
//        Date taskDueDate = task.getDueDate();

        return this.taskDAO.save(task);
    }

    @Override
    public String deleteTaskByID(long taskID) {
        this.taskDAO.deleteById(taskID);
        return "Task deleted successfully!";
    }
}
