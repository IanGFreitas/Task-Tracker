package me.github.IanGFreitas.util;

import me.github.IanGFreitas.entities.Task;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class FileHandler {

    public static void saveTaskList(ArrayList<Task> tasks) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter("src/main/java/me/github/IanGFreitas/tasks/Tasks.json"))) {
            bw.write("[");
            bw.newLine();

            for (Task t : tasks) {

                ZonedDateTime created = t.createdAt().toInstant().atZone(t.createdAt().getTimeZone().toZoneId());
                ZonedDateTime updated = t.updatedAt().toInstant().atZone(t.updatedAt().getTimeZone().toZoneId());
                String comma = ",";

                if (tasks.indexOf(t) == (tasks.size() - 1)){
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
                        """, t.getId(), t.getDescription(), t.getStatus().name(),created.format(DateTimeFormatter.ISO_OFFSET_DATE_TIME), updated.format(DateTimeFormatter.ISO_OFFSET_DATE_TIME), comma);
                bw.write(jsonObject);
            }

            bw.write("]");

        } catch (IOException e) {
            System.out.println(e.getMessage());
        }

    }

}
