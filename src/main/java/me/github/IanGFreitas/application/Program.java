package me.github.IanGFreitas.application;

import me.github.IanGFreitas.Task;

import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.Scanner;
import java.util.Set;

import static me.github.IanGFreitas.entities.Status.todo;

public class Program {
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        String[] userInput;
        String line = "";
        int id = 1;

        Set<Task> tasks = new LinkedHashSet<>();

        boolean run = true;
        while(run){
            line = sc.nextLine();
            userInput = line.split(" ");

            switch (userInput[0]){
                case "add":
                    tasks.add(new Task(id, userInput[1], todo));
                    id++;
                    break;

                case "exit":
                    run = false;
                    break;
            }
        }
        System.out.println("AAAAAA");
        for(Task t : tasks){
            System.out.println(t.toString());
        }

        sc.close();
    }
}