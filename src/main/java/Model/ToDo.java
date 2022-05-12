package Model;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Map;
import java.util.Objects;

/**
 * This class is the data structure of todo
 */
public class ToDo {
    private Map<String, String> parameters;
    private Builder builder;
    private String text;
    private Boolean completed;
    private LocalDate due;
    private Integer priority;
    private String category;
    private Integer id;
    public static final String TODO_TEXT = "--todo-text";
    public static final String COMPLETED = "--completed";
    public static final String DUE = "--due";
    public static final String PRIORITY = "--priority";
    public static final String CATEGORY = "--category";
    public static final Integer TWO = 2;
    public static final Integer SEVEN = 7;

    /**
     * The constructor of ToDo class.
     * @param builder - the builder class.
     * @param parameters - hashMap, contains the information about a ToDo.
     */
    public ToDo(Builder builder, Map<String, String> parameters){
        this.text = builder.text;
        this.completed =  builder.completed;
        this.due = builder.due;
        this.priority = builder.priority;
        this.category = builder.category;
    }

    /**
     * The method to set the ID.
     * @param id : an int, representing the id of a todo
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * The method to set the completed to be true.
     */
    public void setCompleted() {
        this.completed = true;
    }

    /**
     * Getter for the ID.
     * @return integer, representing the id.
     */
    public Integer getID() {
        return this.id;
    }

    /**
     * A method to get the situation of completion.
     * @return boolean, indicating the situation of completion.
     */
    public boolean isCompleted() {
        return this.completed;
    }

    /**
     * Getter of the Text
     * @return string, indicating the Text
     */
    public String getText() {
        return this.text;
    }

    /**
     * Getter of the due
     * @return loca, date, indicating the due
     */
    public LocalDate getDue() {
        return this.due;
    }

    /**
     * Getter of the priority
     * @return integer, indicating the priority
     */
    public Integer getPriority() {
        return this.priority;
    }

    /**
     * Getter of the category
     * @return string, indicating the category
     */
    public String getCategory() {
        return this.category;
    }


    public Boolean getCompleted() {
        return completed;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || this.getClass() != o.getClass()) {
            return false;
        }
        ToDo toDo = (ToDo) o;
        return Objects.equals(this.getText(), toDo.getText()) && Objects
                .equals(this.isCompleted(), toDo.isCompleted()) && Objects
                .equals(this.getDue(), toDo.getDue()) && Objects
                .equals(this.getPriority(), toDo.getPriority()) && Objects
                .equals(this.getCategory(), toDo.getCategory());
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.getText(), this.isCompleted(), this.getDue(), this.getPriority(),
                this.getCategory());
    }

    @Override
    public String toString() {
        return "ToDo{" +
                "id=" + this.id +
                ", text='" + this.text + '\'' +
                ", completed=" + this.completed +
                ", due=" + this.due +
                ", priority=" + this.priority +
                ", category='" + this.category + '\'' +
                '}';
    }

    /**
     * A class of Builder.
     */
    public static class Builder{
        private Map<String, String> parameters;
        private String text;
        private Boolean completed;
        private LocalDate due;
        private Integer priority;
        private String category;

        /**
         * The constructor of the Builder class.
         * @param parameters the input hashMap contains the information about a ToDo.
         */
        public Builder(Map<String, String> parameters){
            this.parameters = parameters;
            this.text=parameters.get(TODO_TEXT.substring(SEVEN));
            this.completed =false;   //Defaulted

            completed(parameters);
            due(parameters);
            priority(parameters);
            category(parameters);
            Build();
        }

        /**
         * A method to get the completed status from input hashMap
         * @param parameters input hashMap
         * @return a Builder
         */
        public  Builder completed(Map<String, String> parameters){
            if (parameters.containsKey(COMPLETED.substring(TWO))
                    && parameters.get(COMPLETED.substring(TWO)) != null)
                this.completed = Boolean.parseBoolean(parameters.get(COMPLETED.substring(TWO)));
            else{
                this.completed = false;}
            return this;
        }
        /**
         * A method to get the due date from input hashMap
         * @param parameters a input hashMap
         * @return a Builder
         */
        public  Builder due(Map<String, String> parameters){
            if (parameters.containsKey(DUE.substring(TWO)) &&
                    parameters.get(DUE.substring(TWO)) != null) {
                String input = parameters.get(DUE.substring(TWO));
                DateTimeFormatter pattern1 = DateTimeFormatter.ofPattern("MM/dd/yyyy");
                DateTimeFormatter pattern2 = DateTimeFormatter.ofPattern("M/dd/yyyy");
                DateTimeFormatter pattern3 = DateTimeFormatter.ofPattern("MM/d/yyyy");
                try {
                    LocalDate ld = LocalDate.parse(input, pattern1);
                    this.due = ld;
                } catch (DateTimeParseException exp) {
                    System.err.println("Failed to parse input using first pattern");
                    try {
                        LocalDate ld = LocalDate.parse(input, pattern2);
                        this.due = ld;
                    } catch (DateTimeParseException exp2) {
                        System.err.println("Failed to parse input using second pattern");
                    }
                    try {
                        LocalDate ld = LocalDate.parse(input, pattern3);
                        this.due = ld;
                    } catch (DateTimeParseException exp3) {
                        System.err.println("Failed to parse input using third pattern");
                    }
                }
            }else{
                this.due = null;}
            return this;
        }

        /**
         * A method to get the priority from input hashMap
         * @param parameters a input hashMap
         * @return a Builder
         */
        public Builder priority( Map<String, String> parameters){
            final Integer DEFAULT_PRIORITY = 3;
            if (parameters.containsKey(PRIORITY.substring(TWO)) &&
                    parameters.get(PRIORITY.substring(TWO)) != null) {
                this.priority = Integer.parseInt(parameters.get(PRIORITY.substring(TWO)));
            } else if ( parameters.get(PRIORITY.substring(TWO)) == null){
                this.priority = null;
            }
            else{
                this.priority = DEFAULT_PRIORITY;
            }
            return this;
        }
        /**
         * A method to get the category from input hashMap
         * @param parameters a input hashMap
         * @return a Builder
         */
        public  Builder category(Map<String, String> parameters){
            if (parameters.containsKey(CATEGORY.substring(TWO))) {
                this.category = parameters.get(CATEGORY.substring(TWO));
            }else{
                this.category = null;}
            return this;
        }
        /**
         * A method to generate a ToDo in the Builder class.
         * @return a ToDo .
         */
        public ToDo Build(){
            return new ToDo(this, this.parameters);
        }
    }
}