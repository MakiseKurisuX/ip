package chatbot;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import task.Todo;
import task.Task;
import task.HeliosException;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

public class TaskListTest {
    private TaskList taskList;

    @BeforeEach
    public void setUp() {
        taskList = new TaskList();
    }

    @Test
    public void testAddTask() {
        Todo todo = new Todo("Buy groceries");
        taskList.addTask(todo);
        
        assertEquals(1, taskList.getSize());
        assertEquals("Buy groceries", taskList.getTask(0).getPureDescription());
    }

    @Test
    public void testRemoveTask() {
        Todo todo1 = new Todo("Read a book");
        Todo todo2 = new Todo("Go for a run");
        
        taskList.addTask(todo1);
        taskList.addTask(todo2);
        
        Task removed = taskList.removeTask(0);
        assertEquals("Read a book", removed.getPureDescription());
        assertEquals(1, taskList.getSize());
    }

    @Test
    public void testMarkTask() throws HeliosException {
        Todo todo = new Todo("Write an essay");
        taskList.addTask(todo);
        
        taskList.markTask(0);
        assertTrue(taskList.getTask(0).getisDone());
    }

    @Test
    public void testUnmarkTask() throws HeliosException {
        Todo todo = new Todo("Complete project");
        taskList.addTask(todo);
        
        taskList.markTask(0);
        taskList.unmarkTask(0);
        
        assertFalse(taskList.getTask(0).getisDone());
    }

    @Test
    public void testMarkTaskInvalidIndex() {
        Exception exception = assertThrows(HeliosException.class, () -> {
            taskList.markTask(0); 
        });
        assertEquals("You input an invalid number", exception.getMessage());
    }
}