package Model;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * This class is the collections of all todos
 */
public class ToDoList implements IToDoList{
    // to do list included full list or filtered list
    private List<ToDo> toDoList;
    private List<ToDo> filteredList;
    private List<ToDo> filteredByComplete;
    private List<ToDo> filteredByCategory;
    public static final String SHOW_INCOMPLETE = "--show-incomplete";
    public static final String SHOW_CATEGORY = "--show-category";
    private static final Integer ID_OFFSET = 1;
    public static final Integer TWO = 2;

    /**
     * Constructor for ToDoList class.
     */
    public ToDoList() {
        this.toDoList = new ArrayList<>();
        this.filteredList = new ArrayList<>();
        this.filteredByComplete = new ArrayList<>();
        this.filteredByCategory = new ArrayList<>();
    }

    /**
     * This function will add a new ToDo.
     * @param toDo - new ToDo to be added.
     */
    public void addToDo(ToDo toDo) {
        this.toDoList.add(toDo);
        toDo.setId(this.getId(toDo));
    }

    /**
     * Get the id of a specific ToDo.
     * @param todo - the ToDo that needs to be found.
     * @return an integer, representing ID of the ToDo.
     */
    public Integer getId(ToDo todo) {
        // because the id starts from 0, 1 need to be added as offset
        return this.toDoList.indexOf(todo) + ID_OFFSET;
    }

    /**
     * Get a List of ToDos.
     * @return a List of ToDos.
     */
    public List<ToDo> getToDoList() {
        return this.toDoList;
    }

    /**
     * This function will filter a list of todos by different conditions.
     * @param condition - filter conditions.
     * @param category - filter category.
     * @return a new list.
     */
    public List<ToDo> filter(String condition, String category) {
        // two filters can be combined if both of them are met
        if (condition.contains(SHOW_INCOMPLETE.substring(TWO)) && !condition.contains(SHOW_CATEGORY.substring(TWO))){
            return this.filterByIncomplete();
        }
        if (!condition.contains(SHOW_INCOMPLETE.substring(TWO)) && condition.contains(SHOW_CATEGORY.substring(TWO))) {
            return this.filterByCategory(category);
        }
        if (condition.contains(SHOW_INCOMPLETE.substring(TWO)) && condition.contains(SHOW_CATEGORY.substring(TWO))) {
            this.filteredByComplete = this.filterByIncomplete();
            this.filteredByCategory = this.filterByCategory(category);
            int size = this.filteredByComplete.size();
            for  (int i = 0; i < size; i ++){
                if (this.filteredByCategory.contains(this.filteredByComplete.get(i))){
                    this.filteredList.add(this.filteredByComplete.get(i));
                }
            }
        }
        return this.filteredList;
    }

    /**
     * This function will filter the list to only include incomplete Todos.
     * @return a new list of todos.
     */
    private List<ToDo> filterByIncomplete() {
        for (ToDo toDo: this.toDoList) {
            if (!toDo.isCompleted()) {
                this.filteredList.add(toDo);
            }
        }
        return this.filteredList;
    }

    /**
     * This function will filter the list to only include Todos with a particular category.
     * @param category - the category for filter.
     * @return a new list of todos.
     */
    private List<ToDo> filterByCategory(String category) {
        for (ToDo toDo: this.toDoList) {
            if (toDo.getCategory()!= null && toDo.getCategory().equals(category)) {
                this.filteredList.add(toDo);
            }
        }
        return this.filteredList;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || this.getClass() != o.getClass()) {
            return false;
        }
        ToDoList toDoList1 = (ToDoList) o;
        return Objects.equals(getToDoList(), toDoList1.getToDoList()) && Objects
                .equals(this.filteredList, toDoList1.filteredList);
    }

    @Override
    public int hashCode() {
        return Objects.hash(getToDoList(), this.filteredList);
    }

    @Override
    public String toString() {
        return "ToDoList{" +
                "toDoList=" + this.toDoList +
                ", partialList=" + this.filteredList +
                '}';
    }
}