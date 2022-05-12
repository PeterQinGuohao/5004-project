import Controller.*;
import Model.*;

public class Main {

  public static final String CSV_FILE = "--csv-file";

  public static void main(String[] args){
    try {
      Command commandParser = new Command(args);
      String file = commandParser.getCommand_map().get(CSV_FILE);
      inputCSV readCSV = new inputCSV();
      ToDoList toDoList = readCSV.readCSV(file);
      TodoFunctionality sysFunction = new TodoFunctionality(commandParser, toDoList);
      sysFunction.SystemFunction();
      WriteCSV writeCSV = new WriteCSV();

      writeCSV.WriteToCSV(file, toDoList);
    }catch (Exception e){
      System.out.println("unknown exception");
    }
  }

}
