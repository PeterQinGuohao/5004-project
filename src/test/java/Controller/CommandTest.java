package Controller;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CommandTest {
    Command testCommand1;
    Command testCommand2;
    Command testCommand3;
    Command testCommand4;


    @BeforeEach
    void setUp() throws InvalidCommandsException {
        String[] strings1 = {"--csv-file", "todos.csv", "--add-todo", "--todo-text", "hw", "--completed", "--due", "2022/04/25"};
        testCommand1 = new Command(strings1);
        String[] strings2 = {"--csv-file", "todos.csv", "--display", "--show-incomplete", "--show-category", "hw"};
        testCommand2 = new Command(strings1);
        String[] strings3 = {"--csv-file", "todos.csv", "--add-todo", "--todo-text", "todo4", "--completed",
                "--due", "7/21/2050", "--priority", "1", "--category", "work", "--complete-todo", "2", "--display",
                "--sort-by-date"};
        testCommand3 = new Command(strings3);
        String[] strings4 = {"--csv-file", "todos.csv", "--add-todo", "--todo-text", "todo4", "--completed",
                "--due", "7/21/2050", "--priority", "1", "--category", "work", "--complete-todo", "2", "--display",
                "--sort-by-priority"};
        testCommand4 = new Command(strings4);
    }

    @Test
    void getCommand_set() {
        assertTrue(testCommand1.getCommand_set().contains("--add-todo"));
        assertTrue(!testCommand1.getCommand_set().contains("--display"));
        assertTrue(testCommand3.getCommand_set().contains("--sort-by-date"));
        assertTrue(testCommand4.getCommand_set().contains("--sort-by-priority"));
    }

    @Test
    void getCommand_map() {
        assertTrue(testCommand1.getCommand_map().containsKey("todo-text"));
        assertEquals("hw",testCommand1.getCommand_map().get("todo-text"));
        assertTrue(testCommand1.getCommand_map().containsKey("due"));
        assertTrue(testCommand1.getCommand_map().containsKey("--csv-file"));
        assertEquals("todos.csv",testCommand1.getCommand_map().get("--csv-file"));
    }

    @Test
    void generator() {
        assertThrows(InvalidCommandsException.class, () -> {
            String[] strings = {"--csv-file", "todos.csv", "--add-todo", "--todo-text", "todo4", "--completed",
                    "--due", "7/21/2050", "--priority", "1", "--category", "work", "--complete-todo", "2",
                    "--display", "--sort-by-priority", "--sort-by-date"};
            Command test1 = new Command(strings);
        });
    }

    @Test
    void generator2() {
        assertThrows(InvalidCommandsException.class, () -> {
            String[] strings = {"--add-todo", "--todo-text", "todo1", "--completed", "--due", "7/21/2050",
                    "--priority", "3", "--category", "work", "--complete-todo", "2", "--display"};
            Command test1 = new Command(strings);
        });
    }

    @Test
    void generator3() {
        assertThrows(InvalidCommandsException.class, () -> {
            String[] strings = {"--csv-file", "todos.csv", "--add-todo", "--completed", "--due", "7/21/2050",
                    "--priority", "3", "--category", "work", "--complete-todo", "2", "--display"};
            Command test1 = new Command(strings);
        });
    }

    @Test
    void generator4() {
        assertThrows(InvalidCommandsException.class, () -> {
            String[] strings = {"--csv-file", "todos.csv", "--add-todo", "--todo-text", "todo1", "--completed",
                    "--due", "7/21/2050", "--priority", "4", "--category", "work", "--complete-todo", "2", "--display"};
            Command test1 = new Command(strings);
        });
    }

    @Test
    void generator5() {
        assertThrows(InvalidCommandsException.class, () -> {
            String[] strings = {"--csv-file", "--complete-todo", "2","todos", "--todo-text", "todo1", "--completed", "--due",
                    "4/24/2022", "--priority", "3", "--category", "work",  "--display"};
            Command test1 = new Command(strings);
        });
    }

    @Test
    void generator6() {
        assertThrows(InvalidCommandsException.class, () -> {
            String[] strings ={"--csv-file", "todos.csv", "--add-todo", "--todo-text", "todo1", "--completed", "--due",
                    "7/21/2050", "--priority", "3", "--category", "work", "--complete-todo", "2", "--display",
                    "--sort-by-date", "--sort-by-priority"};
            Command test1 = new Command(strings);
        });

    }

    @Test
    void check() {
        assertThrows(InvalidCommandsException.class, () -> {
            String[] strings ={"--csv-file", "todos.csv", "--add-todo", "--todo-text", "todo1", "--completed", "--due",
                    "7/21/2050", "--priority", "3", "--category", "work", "--complete-todo", "2", "--display",
                    "--sort-by-priority", "--sort-by-date"};
            Command test1 = new Command(strings);
        });
    }

    @Test
    void check2() {
        assertThrows(InvalidCommandsException.class, () -> {
            String[] strings ={"--csv-file", "todos.csv", "--todo-text", "todo1", "--due", "7/21/2050",
                    "--category", "work", "--complete-todo", "2", "--display"};
            Command test1 = new Command(strings);
        });
    }

    @Test
    void check3() {
        assertThrows(InvalidCommandsException.class, () -> {
            String[] strings ={"--csv-file", "todos", "--todo-text", "todo1", "--completed", "--due", "7/21/2050",
                    "--priority", "3", "--category", "work", "--complete-todo", "2", "--display"};
            Command test1 = new Command(strings);
        });
    }

    @Test
    void check4() {
        assertThrows(InvalidCommandsException.class, () -> {
            String[] strings ={"--csv-file", "todos.csv", "--todo-text", "todo1", "--due",
                    "7/21/2050", "--priority", "3", "--category", "work", "--complete-todo", "2", "--display"};
            Command test1 = new Command(strings);
        });
    }

    @Test
    void check5() {
        assertThrows(InvalidCommandsException.class, () -> {
            String[] strings ={"--file", "todos", "--list", "todo1", "--completed", "--due", "7/21/2050",
                    "--priority", "3", "--category", "work", "--complete-todo", "2", "--display"};
            Command test1 = new Command(strings);
        });
    }

    @Test
    void check6() {
        assertThrows(InvalidCommandsException.class, () -> {
            String[] strings ={"--csv-file", "todos.csv", "--todo-text", "--todo1", "--completed", "--due",
                    "--7/21/2050", "--priority", "3", "--category", "work", "--complete-todo", "2", "--display"};
            Command test1 = new Command(strings);
        });
    }

    @Test
    void check7() {
        assertThrows(InvalidCommandsException.class, () -> {
            String[] strings = {"--csv-file", "todos.csv", "--sort-by-priority", "--todo-text", "todo1",
                    "--completed", "--due", "7/21/2050", "--priority", "3", "--category", "work", "--complete-todo",
                    "2", "--add-todo"};
            Command test1 = new Command(strings);
        });
    }
    @Test
    void check8() {
        assertThrows(InvalidCommandsException.class, () -> {
            String[] strings ={"--csv-file", "todos", "--todo-text", "todo1", "--completed", "--due", "7/21/2050",
                    "--priority", "3", "--category", "work", "--complete-todo", "2", "--display"};
            Command test1 = new Command(strings);
        });
    }
    @Test
    void check9() {
        assertThrows(InvalidCommandsException.class, () -> {
            String[] strings ={"--csv-file", "todos.csv", "--todo-text", "todo1",
                    "--due", "7/21/2050", "--priority", "3", "--category", "work", "--complete-todo", "2", "--display"};
            Command test1 = new Command(strings);
        });
    }

    @Test
    void testToDo() {
        assertTrue(testCommand1.ContainsAddToDo());
    }

    @Test
    void testEquals() throws InvalidCommandsException {
        assertTrue(testCommand1.equals(testCommand1));
        assertFalse(testCommand1.equals(null));
        assertFalse(testCommand1.equals("null"));
        String[] strings2 = {"--csv-file", "todos.csv", "--add-todo", "--todo-text", "hw", "--completed",
                "--due", "2022/04/25"};
        Command testCommand = new Command(strings2);
        ;
        assertTrue(testCommand1.equals(testCommand));
    }

    @Test
    void testHashCode() {
        assertEquals(testCommand1.hashCode(), testCommand1.hashCode());
    }

    @Test
    void testToString() {
        assertEquals(testCommand1.toString(), testCommand1.toString());
    }


}