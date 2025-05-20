package me.github.IanGFreitas.util;

import me.github.IanGFreitas.entities.Task;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static me.github.IanGFreitas.entities.Status.*;

public class ListTasks {
    private ArrayList<Task> tasks;
    private String argument;

    public ListTasks(ArrayList<Task> tasks, String argument) {
        this.tasks = tasks;
        this.argument = argument;
    }

    public void listTask(){

        switch (argument){
            case "done":
                List<Task> doneTasks = tasks.stream().filter(e -> e.getStatus() == done).toList();
                doneTasks.forEach(System.out::println);
                break;

            case "todo":
                List<Task> todoTasks = tasks.stream().filter(e -> e.getStatus() == todo).toList();
                todoTasks.forEach(System.out::println);
                break;

            case "inprogress":
                List<Task> inprogressTasks = tasks.stream().filter(e -> e.getStatus() == inprogress).toList();
                inprogressTasks.forEach(System.out::println);
                break;

            default:
                tasks.forEach(System.out::println);
                break;
        }
    }
}
