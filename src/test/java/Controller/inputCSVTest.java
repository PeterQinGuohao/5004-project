package Controller;

import Model.ToDoList;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class inputCSVTest {
    inputCSV csv;

    @BeforeEach
    void setUp() {
        csv = new inputCSV();
    }

    @Test
    void readCSV() {
        ToDoList toDoList = csv.readCSV("todos.csv");
    }
}