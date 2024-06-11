package genspark.TaskManagerapi.TaskManagerDemo.Repository;

import genspark.TaskManagerapi.TaskManagerDemo.Entity.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TaskDAO extends JpaRepository<Task, Long> {

}
