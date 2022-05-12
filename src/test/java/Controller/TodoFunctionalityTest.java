package Controller;

import static org.junit.jupiter.api.Assertions.*;

import Model.*;
import java.util.HashMap;
import java.util.Map;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class TodoFunctionalityTest {

  TodoFunctionality testFunction1;
  TodoFunctionality testFunction2;
  Command testCommand1;
  inputCSV testInput1;
  ToDoList testTodoList1;
  ToDoList testTodoList2;
  String[] testArg;
  String[] testArg2;

  @BeforeEach
  void setUp() throws InvalidCommandsException {
    testArg = new String[]{"--csv-file", "todos.csv", "--display", "--sort-by-priority"};
    testCommand1 = new Command(testArg);
    String fileName = testCommand1.getCommand_map().get("--csv-file");
    testInput1 = new inputCSV();
    testTodoList1 = testInput1.readCSV(fileName);
    testTodoList2 = testInput1.readCSV(fileName);
    Map<String, String> newTodo = new HashMap<>();
    newTodo.put("text", "5004");
    newTodo.put("completed", "true");
    newTodo.put("due", "01/01/2022");
    newTodo.put("priority", "1");
    newTodo.put("category", "neu");
    ToDo testTodo = new ToDo.Builder(newTodo).Build();
    testTodoList1 = new ToDoList();
    testTodoList1.addToDo(testTodo);
    testFunction1 = new TodoFunctionality(testCommand1, testTodoList1);
  }

  @Test
  void systemFunction() throws InvalidCommandsException {
    testArg = new String[]{"--csv-file", "todos.csv", "--add-todo", "--todo-text", "cs508",
                           "--due", "01/01/2020", "--priority", "1", "--completed", "--category", "neu"};
    testCommand1 = new Command(testArg);
    testFunction1 = new TodoFunctionality(testCommand1, testTodoList1);
    testFunction1.SystemFunction();

    testArg = new String[]{"--csv-file", "todos.csv", "--display", "--sort-by-priority"};
    testCommand1 = new Command(testArg);
    testFunction1 = new TodoFunctionality(testCommand1, testTodoList1);
    testFunction1.SystemFunction();

    testArg2 = new String[]{"--csv-file", "todos.csv", "--complete-todo", "1"};
    testCommand1 = new Command(testArg2);
    testFunction2 = new TodoFunctionality(testCommand1, testTodoList2);
    testFunction2.completeItem(1);

    testArg = new String[]{"--csv-file", "todos.csv", "--display", "--show-incomplete"};
    testCommand1 = new Command(testArg);
    testFunction1 = new TodoFunctionality(testCommand1, testTodoList1);
    testFunction1.SystemFunction();

    testArg = new String[]{"--csv-file", "todos.csv", "--display", "--show-category", "school"};
    testCommand1 = new Command(testArg);
    testFunction1 = new TodoFunctionality(testCommand1, testTodoList1);
    testFunction1.SystemFunction();

    testArg = new String[]{"--csv-file", "todos.csv", "--display", "--sort-by-date"};
    testCommand1 = new Command(testArg);
    testFunction1 = new TodoFunctionality(testCommand1, testTodoList1);
    testFunction1.SystemFunction();

  }

  @Test
  void testEquals() {
    assertTrue(testFunction1.equals(testFunction1));
    assertFalse(testFunction1.equals(null));
    assertFalse(testFunction1.equals(testArg));
  }

  @Test
  void testHashCode() {
    assertEquals(1925059485, testFunction1.hashCode());
  }

  @Test
  void testToString() {
    assertEquals("TodoFunctionality{commandProcessor=Command{command_set=[--sort-by-priority, --display], command_map={--csv-file=todos.csv}},"
        + " toDoList=ToDoList{toDoList=[ToDo{id=1, text='5004',"
        + " completed=true, due=2022-01-01, priority=1, category='neu'}], partialList=[]}}", testFunction1.toString());
  }
}