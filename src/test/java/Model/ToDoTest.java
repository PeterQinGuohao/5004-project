package Model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class ToDoTest {
    Map<String, String> parameters;
    ToDo todo;

    @BeforeEach
    void setUp() {
        parameters = new HashMap<>();
        parameters.put("id", "1");
        parameters.put("text", "finish hw9");
        parameters.put("completed", "FALSE");
        todo = new ToDo.Builder(parameters).Build();
    }

    @Test
    void setId() {
        todo.setId(5);
        assertEquals(5, todo.getID());
    }

    @Test
    void setCompleted() {
        todo.setCompleted();
        assertTrue(todo.isCompleted());
    }

    @Test
    void getID() {
        assertEquals(null, todo.getID());
    }


    @Test
    void isCompleted() {
        assertFalse(todo.isCompleted());
    }

    @Test
    void getText() {
    }

    @Test
    void getDue() {
        assertEquals("finish hw9", todo.getText());
    }

    @Test
    void getPriority() {
        assertNull(todo.getPriority());
    }

    @Test
    void getCategory() {
        assertNull(todo.getCategory());
    }

    @Test
    public void testEquals() {
        ToDo newTodo = new ToDo.Builder(parameters).Build();
        assertEquals(newTodo, todo);
        assertNotEquals(todo, todo.getPriority());
        assertNotNull(todo);
        Map<String, String> newParameters = new HashMap<>();
        newParameters.put("id", "10");
        newParameters.put("text", "Polish resume");
        newParameters.put("completed", "TRUE");
        newParameters.put("priority", "1");
        ToDo myToDo = new ToDo.Builder(newParameters).Build();
        assertNotEquals(todo, myToDo);
    }
}
