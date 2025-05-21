package me.github.IanGFreitas.util;

import me.github.IanGFreitas.entities.Status;
import me.github.IanGFreitas.entities.Task;

import java.io.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class FileHandler {

    public static boolean isDirectoryEmpty(){
        File directory = new File("src/main/java/me/github/IanGFreitas/tasks");
        File[] fileList = directory.listFiles();
        assert fileList != null;

        return fileList.length == 0;
    }

    public static void saveTaskList(ArrayList<Task> tasks) {
        DateTimeFormatter dtf = DateTimeFormatter.ofLocalizedPattern("dd/MM/yyyy HH:mm");

        try (BufferedWriter bw = new BufferedWriter(new FileWriter("src/main/java/me/github/IanGFreitas/tasks/Tasks.json"))) {
            bw.write("[");
            bw.newLine();

            for (Task t : tasks) {
                String comma = ",";

                if (tasks.indexOf(t) == (tasks.size() - 1)) {
                    comma = "";
                }

                String jsonObject = String.format("""
                            {
                                "id": %d,
                                "description": "%s",
                                "status": "%s",
                                "createdAt": "%s",
                                "updatedAt": "%s"
                            }%s
                        """, t.getId(), t.getDescription(), t.getStatus().name(), t.createdAt(), t.updatedAt(), comma);
                bw.write(jsonObject);
            }

            bw.write("]");

        } catch (IOException e) {
            System.out.println(e.getMessage());
        }

    }

    private static boolean ignoreLine(String str) {
        String[] ignoredChar = {"{", "}", "[", "]"};

        for (String igChar : ignoredChar) {
            if (igChar.equals(str)) {
                return true;
            }
        }
        return false;
    }

    public static ArrayList<Task> loadTasks() {
        ArrayList<Task> loadedTasks = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader("src/main/java/me/github/IanGFreitas/tasks/Tasks.json"))) {
            String line = "";

            int id = 0;
            String description = "";
            Status status = null;
            LocalDate createdAt = null;
            LocalDate updatedAt = null;

            line = br.readLine();
            while (line != null) {

                if (!ignoreLine(line)) {
                    String[] splitLine = line.split(":");
                    ArrayList<String> halfClearedStr = new ArrayList<>();
                    ArrayList<String> clearedStr = new ArrayList<>();

                    for (String str : splitLine) {
                        halfClearedStr.add(str.replace("\"", ""));

                        for (String halfStr : halfClearedStr) {
                            clearedStr.add(halfStr.replace(",", ""));
                        }
                    }

                    switch (clearedStr.getFirst()) {
                        case "id":
                            id = Integer.parseInt(clearedStr.get(1));
                            break;

                        case "description":
                            description = clearedStr.get(1);
                            break;

                        case "status":
                            status = Status.valueOf(clearedStr.get(1));
                            break;

                        case "createdAt":
                            createdAt = LocalDate.parse(clearedStr.get(1));
                            break;

                        case "updatedAt":
                            updatedAt = LocalDate.parse(clearedStr.get(1));
                            break;
                    }

                }
                loadedTasks.add(new Task(id, description, status, createdAt, updatedAt));
                line = br.readLine();
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }

        return loadedTasks;
    }

}
