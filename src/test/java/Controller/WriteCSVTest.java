package Controller;

import static org.junit.jupiter.api.Assertions.*;

import Model.ToDo;
import Model.ToDoList;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class WriteCSVTest {

  WriteCSV testWriteCSV;
  ToDoList testTodoList1;
  String testFilePath = "testWrite.csv";
  String testFilePath2 = "testWrite1.csv";

  @BeforeEach
  void setUp() {
    Map<String, String> newTodo = new HashMap<>();
    newTodo.put("text", "5004");
    newTodo.put("completed", "true");
    newTodo.put("due", "01/01/2022");
    newTodo.put("priority", "1");
    newTodo.put("category", "neu");
    ToDo testTodo = new ToDo.Builder(newTodo).Build();
    testTodoList1 = new ToDoList();
    testTodoList1.addToDo(testTodo);
    testWriteCSV = new WriteCSV();
  }

  @Test
  void writeToCSV() throws IOException {
    testWriteCSV.WriteToCSV(testFilePath, testTodoList1);
    BufferedReader reader = new BufferedReader(new FileReader(testFilePath));
    String line = reader.readLine();
    assertEquals("\"id\",\"text\",\"completed\",\"due\",\"priority\",\"category\"",line);
  }

}