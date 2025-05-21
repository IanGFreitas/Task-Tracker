package me.github.IanGFreitas.application;

import me.github.IanGFreitas.entities.Task;
import me.github.IanGFreitas.util.FileHandler;
import me.github.IanGFreitas.util.ListTasks;

import java.util.*;

import static me.github.IanGFreitas.entities.Status.*;

public class Program {
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        String[] userInput;
        String line = "";
        int id = 1;

        ArrayList<Task> tasks = new ArrayList<>();

        if (!FileHandler.isDirectoryEmpty()){
            tasks = FileHandler.loadTasks();
        }
        
        boolean run = true;
        while(run){
            line = sc.nextLine();
            userInput = line.split(" ");

            switch (userInput[0]){
                case "add":
                    String[] taskDescription = Arrays.copyOfRange(userInput, 1, userInput.length);
                    tasks.add(new Task(id, String.join(" ", taskDescription), todo));
                    id++;
                    break;

                case "delete":
                    tasks.remove(Integer.parseInt(userInput[1])-1);
                    break;

                case "update":
                    String[] newDescription = Arrays.copyOfRange(userInput, 2, userInput.length);
                    tasks.get(Integer.parseInt(userInput[1])-1).updateTask(String.join(" ", newDescription));
                    break;

                case "mark-in-progress":
                    tasks.get(Integer.parseInt(userInput[1])-1).setStatus(inprogress);
                    break;

                case "mark-done":
                    tasks.get(Integer.parseInt(userInput[1])-1).setStatus(done);
                    break;

                case "list":
                    String listSubArgument = "";
                    if (userInput.length > 1) {
                        listSubArgument = userInput[1];
                    }

                    ListTasks list = new ListTasks(tasks, listSubArgument);
                    list.listTask();
                    break;

                case "exit":
                    run = false;
                    break;
            }
        }
        FileHandler.saveTaskList(tasks);
        sc.close();
    }
}