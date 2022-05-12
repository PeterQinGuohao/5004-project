package Model;

import java.util.List;

/**
 * Interface of ToDoList class.
 */
public interface IToDoList {

    /**
     * Add a new ToDo into the collection.
     * @param toDo - new ToDo to be added.
     */
    void addToDo(ToDo toDo);

    /**
     * Get the id of ToDo in the list when first generate.
     * @param todo - the ToDo needs to find id.
     * @return ID of the requested ToDo.
     */
    Integer getId(ToDo todo);

    /**
     * Get the collection as a List of ToDos.
     * @return the collection as a List of ToDos.
     */
    List<ToDo> getToDoList();

    /**
     * Filter a list by different conditions.
     * @param condition - filter conditions.
     * @param category - filter category.
     * @return a new list after filter.
     */
    List<ToDo> filter(String condition, String category);
}