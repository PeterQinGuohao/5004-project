package Controller;

import java.util.*;
import Model.*;

public class TodoFunctionality {
  public static final String ADD_TODO = "--add-todo";
  public static final String COMPLETE_TODO = "--complete-todo";
  public static final String DISPLAY = "--display";
  public static final String SHOW_INCOMPLETE = "--show-incomplete";
  public static final String SHOW_CATEGORY = "--show-category";
  public static final String SORT_BY_DATE = "--sort-by-date";
  public static final String SORT_BY_PRIORITY = "--sort-by-priority";

  private Command commandProcessor;
  private ToDoList toDoList;

  /**
   * Construct a new TodoFunctionality object with specified commandProcessor and toDoList
   * @param commandProcessor
   * @param toDoList
   */
  public TodoFunctionality(Command commandProcessor, ToDoList toDoList) {
    this.commandProcessor = commandProcessor;
    this.toDoList = toDoList;
  }

  /**
   * The system must support the following functionality:
   * - Add a new todo
   * - Complete an existing todo
   * - Display todos
   */
  public void SystemFunction() {
    if (this.containAddCommand()) {
      ToDo newTodo = new ToDo.Builder(this.getAddText()).Build();
      this.toDoList.addToDo(newTodo);
    }
    if (this.containCompleteCommand()) {
      int id = Integer.parseInt(this.commandProcessor.getCommand_map().get(COMPLETE_TODO));
      this.completeItem(id);
    }
    if (this.containDisplayCommand()) {
      this.display();
    }
  }

  /**
   * Mark the Todo with the provided ID as complete.
   */
  public void completeItem(int id) {
    for (ToDo todo : this.toDoList.getToDoList()) {
      if (id == todo.getID()) {
        todo.setCompleted();
      }
    }
  }

  /**
   * Create a new ToDo
   * @return a Map that contains information
   */
  public Map<String, String> getAddText() {
    List<String> newToDo = Arrays.asList("text", "completed", "due", "priority", "category");
    Map<String, String> newMap = new HashMap<>();
    for (String str : this.commandProcessor.getCommand_map().keySet()) {
      if (newToDo.contains(str)) {
        newMap.put(str, this.commandProcessor.getCommand_map().get(str));
      }
    }
    if (this.commandProcessor.getCommand_set().contains("completed")) {
      newMap.put("completed", null);
    }
    return newMap;
  }

  /**
   * Display todos.
   */
  public void display() {
    List<ToDo> displayList = new ArrayList<>();
    displayList = this.toDoList.getToDoList();
    if (this.displayIncomplete()) {
      displayList = this.toDoList.filter(SHOW_INCOMPLETE, null);
    }
    else if (this.displayCategory()) {
      displayList = this.toDoList.filter(SHOW_CATEGORY, this.commandProcessor.getCommand_map().get(SHOW_CATEGORY));
    }
    else if (this.displayDate()) {
      Collections.sort(displayList, Comparator.nullsLast(
          Comparator.comparing(
              ToDo::getDue, Comparator.nullsLast(Comparator.naturalOrder())
          )
      ));
    }
    else if (this.displayPriority()) {
      Collections.sort(displayList, Comparator.nullsLast(
          Comparator.comparing(
              ToDo::getPriority, Comparator.nullsLast(Comparator.naturalOrder())
          )
      ));
    }
    else {
      displayList = this.toDoList.getToDoList();
    }
    for (ToDo todo : displayList) {
      System.out.println(todo);
    }
  }

  /**
   * Check if the command contains Incomplete
   * @return true if contained, otherwise return false
   */
  public boolean displayIncomplete() {
    return this.commandProcessor.getCommand_set().contains(SHOW_INCOMPLETE);
  }

  /**
   * Check if the command contains Category
   * @return true if contained, otherwise return false
   */
  public boolean displayCategory() {
    return this.commandProcessor.getCommand_map().containsKey(SHOW_CATEGORY);
  }

  /**
   * Check if the command contains Sort By Date
   * @return true if contained, otherwise return false
   */
  public boolean displayDate() {
    return this.commandProcessor.getCommand_set().contains(SORT_BY_DATE);
  }

  /**
   * Check if the command contains Sort By Priority
   * @return true if contained, otherwise return false
   */
  public boolean displayPriority() {
    return this.commandProcessor.getCommand_set().contains(SORT_BY_PRIORITY);
  }

  /**
   * Check if the command contains Add
   * @return true if contained, otherwise return false
   */
  public boolean containAddCommand() {
    return this.commandProcessor.getCommand_set().contains(ADD_TODO);
  }

  /**
   * Check if the command contains Complete
   * @return true if contained, otherwise return false
   */
  public boolean containCompleteCommand() {
    return this.commandProcessor.getCommand_map().containsKey(COMPLETE_TODO);
  }

  /**
   * Check if the command contains Display
   * @return true if contained, otherwise return false
   */
  public boolean containDisplayCommand() {
    return this.commandProcessor.getCommand_set().contains(DISPLAY);
  }

  /**
   * check whether two objects are same
   * @param o object to be compared
   * @return true if same, otherwise return false
   */
  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    TodoFunctionality that = (TodoFunctionality) o;
    return Objects.equals(commandProcessor, that.commandProcessor)
        && Objects.equals(toDoList, that.toDoList);
  }

  /**
   * return hashcode of the object
   * @return hashcode of the object
   */
  @Override
  public int hashCode() {
    return Objects.hash(commandProcessor, toDoList);
  }

  /**
   * return a string that represents the object
   * @return a string
   */
  @Override
  public String toString() {
    return "TodoFunctionality{" +
        "commandProcessor=" + commandProcessor +
        ", toDoList=" + toDoList +
        '}';
  }
}
