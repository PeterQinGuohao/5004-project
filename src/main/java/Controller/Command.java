package Controller;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Objects;

/**
 * This class Command stores and determines the command line arguments.
 */
public class Command {
    public static final String CSV_FILE = "--csv-file";
    public static final String ADD_TODO = "--add-todo";
    public static final String TODO_TEXT = "--todo-text";
    public static final String COMPLETED = "--completed";
    public static final String DUE = "--due";
    public static final String PRIORITY = "--priority";
    public static final String CATEGORY = "--category";
    public static final String COMPLETE_TODO = "--complete-todo";
    public static final String DISPLAY = "--display";
    public static final String SHOW_INCOMPLETE = "--show-incomplete";
    public static final String SHOW_CATEGORY = "--show-category";
    public static final String SORT_BY_DATE = "--sort-by-date";
    public static final String SORT_BY_PRIORITY = "--sort-by-priority";
    public static final Integer TWO = 2;
    private HashSet<String> command_set;
    private HashMap<String, String> command_map;

    /**
     * The constructor of the class
     * @param args the input arguments
     * @throws InvalidCommandsException if the command are not valid.
     */
    public Command(String[] args) throws InvalidCommandsException {
        command_set = new HashSet<>();
        command_map = new HashMap<>();
        this.generator(args);
        this.check();
    }

    /**
     * The getter of the command set
     * @return the command set
     */
    public HashSet<String> getCommand_set() {
        return command_set;
    }

    /**
     * The getter of the command map
     * @return the command map
     */
    public HashMap<String, String> getCommand_map() {
        return command_map;
    }

    /**
     * Determine it contains addTodo or not
     * @return true if it contains addTodo, false otherwise
     */
    protected boolean ContainsAddToDo() {
        return (this.command_set.contains(COMPLETED) ||
                this.command_map.containsKey(PRIORITY.substring(TWO)) ||
                this.command_map.containsKey(CATEGORY.substring(TWO)));
    }

    /**
     * Determine it contains display or not
     * @return true if it contains display, false otherwise
     */
    protected boolean ContainsDisplay() {
        return (this.command_map.containsKey(SHOW_CATEGORY) ||
                this.command_set.contains(SHOW_INCOMPLETE) ||
                this.command_set.contains(SORT_BY_DATE) ||
                this.command_set.contains(SORT_BY_PRIORITY));
    }

    /**
     * The generator of the class
     * @param args the input arguments
     * @throws InvalidCommandsException if the commands are not valid
     */
    public void generator(String[] args) throws InvalidCommandsException {

        for (int i = 0; i < args.length; i++) {
            switch (args[i]) {
                case CSV_FILE:
                    String lastFourDigits = args[i + 1].substring(args[i + 1].length() - 4);
                    if (args[i + 1].contains("--") || !lastFourDigits.equals(".csv")) {
                        throw new InvalidCommandsException(args[i] + " is not a valid csv file.");
                    } else
                        this.command_map.put(CSV_FILE, args[++i]);
                    break;

                case COMPLETED:
                case ADD_TODO:
                case DISPLAY:
                case SHOW_INCOMPLETE:
                    this.command_set.add(args[i]);
                    break;

                case TODO_TEXT:
                case DUE:
                case COMPLETE_TODO:
                case CATEGORY:
                case SHOW_CATEGORY:
                    if (args[i + 1].contains("--")) {
                        throw new InvalidCommandsException(
                                "The value after " + args[i] + " is invalid.");
                    } else
                        command_map.put(args[i].substring(TWO), args[++i]);
                    break;
                case PRIORITY:
                    if (args[i + 1].contains("1") || args[i + 1].contains("2") || args[i + 1].contains("3")) {
                        command_map.put(args[i].substring(TWO), args[++i]);
                    } else
                        throw new InvalidCommandsException(
                                "The value of " + args[i] + " is invalid.");
                    break;
                case SORT_BY_DATE:
                    if (this.command_set.contains(SORT_BY_PRIORITY)) {
                        throw new InvalidCommandsException(args[i] + " is not valid with --sort-by-priority.");
                    } else
                        this.command_set.add(args[i]);
                    break;

                case SORT_BY_PRIORITY:
                    if (this.command_set.contains(SORT_BY_DATE)) {
                        throw new InvalidCommandsException(args[i] + " is not valid with --sort-by-date.");
                    } else
                        this.command_set.add(args[i]);
                    break;

                default:
                    throw new InvalidCommandsException(args[i]+ " is invalid.");
            }
        }
    }

    /**
     * The checker of the class
     * @throws InvalidCommandsException if the commands are not valid
     */
    public void check() throws InvalidCommandsException {

        if (!this.command_map.containsKey(CSV_FILE)) {
            throw new InvalidCommandsException("It should contain csv file");
        }

        if (this.command_set.contains(ADD_TODO)) {
            boolean foundTodoText = this.command_map.containsKey(TODO_TEXT.substring(TWO));
            if (!foundTodoText) {
                throw new InvalidCommandsException("It should contain --todo-text");
            }
        }


        if (this.ContainsAddToDo()) {
            boolean foundAddTodo = this.command_set.contains(ADD_TODO);
            if (!foundAddTodo) {
                throw new InvalidCommandsException("It should contain --add-todo option");
            }
        }

        if (this.ContainsDisplay()) {
            boolean foundDisplay = this.command_set.contains(DISPLAY);
            if (!foundDisplay) {
                throw new InvalidCommandsException("It should contain --display option");
            }
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Command command = (Command) o;
        return Objects.equals(command_set, command.command_set) && Objects.equals(command_map, command.command_map);
    }

    @Override
    public int hashCode() {
        return Objects.hash(command_set, command_map);
    }

    @Override
    public String toString() {
        return "Command{" +
                "command_set=" + command_set +
                ", command_map=" + command_map +
                '}';
    }
}
