package genspark.TaskManagerapi.TaskManagerDemo.Entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.*;

import java.sql.Date;

// @Entity automatically makes SQL tables for you.
// @Getter adds getters for you, but they are not shown in the file.
// @Setter adds setters for you, but they are not shown in the file.
// etc...
@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Task {

    // @Id informs the table that the field below is the primary key.
    // @GeneratedValue(strategy = GenerationType.AUTO) sets the key as auto_increment
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long taskID;
    private String title;
    private String description;
    private Date dueDate;

    public long getTaskID() {
        return taskID;
    }

    public void setTaskID(long taskID) {
        this.taskID = taskID;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getDueDate() {
        return dueDate;
    }

    public void setDueDate(Date dueDate) {
        this.dueDate = dueDate;
    }
}
