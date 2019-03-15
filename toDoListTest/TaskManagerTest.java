package toDoListTest;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;
import java.util.ArrayList;

import org.junit.Assert;
import org.junit.jupiter.api.Test;

import toDoList.Task;
import toDoList.TaskManager;

class TaskManagerTest {
	
	private TaskManager taskManager= new TaskManager();

	@Test
	void addNewTaskAddsTask() {
		//sets the value in addTask method  and checks with assertEqual method
		taskManager.addTaskToTodoList("project1", "testTitle", LocalDate.now(), "Done");
	
		ArrayList<Task> tempTask = taskManager.toDoList.get("project1");
		Task task  =tempTask.get(0);
		
		assertEquals(LocalDate.now(),task.getDate());
	}
	
	
	@Test
	void addNewTaskChecksForNullValue() {
		
		taskManager.addTaskToTodoList("project1", "testTitle", LocalDate.now(), "Done");
	
		ArrayList<Task> tempTask = taskManager.toDoList.get("project1");
		for(Task task: tempTask) {
			Assert.assertNotNull(task);
		}
		
	}

}
