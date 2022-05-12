package Controller;

import Model.ToDo;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.io.*;
import Model.ToDoList;

/**
 * This class is the information of CSV file as input
 */
public class inputCSV {
    /**
     * Constructor of the inputCSV class
     */
    public inputCSV() {
    }

    /**
     * @param csvFileName the name of CSV file
     * @return a ToDoList obtained by the input CSV file
     */
    public ToDoList readCSV(String csvFileName) {
        Map<Integer, String> lineMap = new HashMap<>();
        ArrayList<Map<String, String>> todos = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(csvFileName))) {
            String line;
            line = reader.readLine();
            line = line.substring(1, line.length() - 1);

            String[] columns = line.split("\",\"");
            for (int i = 0; i < columns.length; i++) {
                lineMap.put(i, columns[i]);
            }
            int count = 0;
            while ((line = reader.readLine()) != null) {
                Map<String, String> todo = new HashMap<>();

                if(line.length() <= 6) break;

                String[] filledIn = line.substring(1, line.length() - 1).split("\",\"");
                System.out.println(Arrays.toString(filledIn));
                for (int i = 0; i < columns.length; i++) {
                    if (filledIn[i].equals("?")) {
                        todo.put(lineMap.get(i), null);
                    } else {
                        todo.put(lineMap.get(i), filledIn[i]);
                    }
                }
                todos.add(todo);
                count++;
            }
        } catch (
                IndexOutOfBoundsException e) {
            System.out.println("customized index out of bound exception");
        }catch (FileNotFoundException e){
            System.out.println("File not Found exception");
        }catch (IOException e){
            System.out.println("general io exception");
        }
        ToDoList ans = new ToDoList();
        for (Map<String, String> temp : todos) {
            ToDo todo = new ToDo.Builder(temp).Build();
            ans.addToDo(todo);
        }
        return ans;
    }
}
