package task;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TodoTest {

    @Test
    public void testTodoCreation() {
        Todo todo = new Todo("Buy groceries");
        
        assertEquals("Buy groceries", todo.getPureDescription());
        
        assertEquals("T", todo.getType());
        
        assertEquals(false, todo.getisDone());
    }

    @Test
    public void testMarkAsDone() {
        Todo todo = new Todo("Read a book");
        todo.setisDone(true); 
        
        assertEquals(true, todo.getisDone());
    }
}

