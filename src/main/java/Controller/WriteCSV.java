package Controller;

import java.io.FileOutputStream;

import Model.*;

public class WriteCSV {

  /**
   * Construct a WriteCSV object
   */
  public WriteCSV() {
  }

  /**
   * Write a new CSV file with given information
   * @param filePath the path of new CSV file
   * @param toDoList the list that contains information
   */
  public void WriteToCSV(String filePath, ToDoList toDoList) {
    StringBuilder inputBuffer = new StringBuilder();
    final String header = "\"id\",\"text\",\"completed\",\"due\",\"priority\",\"category\"\n";
    inputBuffer.append(header);
    String line;
    for (ToDo toDo : toDoList.getToDoList()) {
      line = String.format(
          "\"%d\",\"%s\",\"%b\",\"%s\",\"%s\",\"%s\"",
          toDo.getID(),
          toDo.getText(),
          toDo.getCompleted(),
          toDo.getDue() == null ? "?" : toDo.getDue(),
          toDo.getPriority() == null ? "?" : Integer.toString(toDo.getPriority()),
          toDo.getCategory() == null ? "?" : toDo.getCategory());
      inputBuffer.append(line).append(System.lineSeparator());

    }
    try {
      FileOutputStream fileOut = new FileOutputStream(filePath);
      fileOut.write(inputBuffer.toString().getBytes());
      fileOut.close();
    } catch (Exception e) {
      System.out.println("Failed to write result to file.");
    }
  }

}
