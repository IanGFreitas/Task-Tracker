package me.github.IanGFreitas.util;

import me.github.IanGFreitas.entities.Task;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Calendar;

public class FileHandler {

    private static boolean isDirectoryEmpty(){
        File directory = new File("D:\\Task Tracker\\Task Tracker\\src\\main\\java\\me\\github\\IanGFreitas\\tasks");
        File[] fileList = directory.listFiles();

        if (fileList == null){
            return true;
        } else {
            return false;
        }
    }

    public static void saveTaskList(ArrayList<Task> tasks){
        if (isDirectoryEmpty()){

            try (BufferedWriter bw = new BufferedWriter(new FileWriter("D:\\Task Tracker\\Task Tracker\\src\\main\\java\\me\\github\\IanGFreitas\\tasks\\Tasks.json"))){
                for (Task t : tasks){
                    ZonedDateTime created = t.createdAt().toInstant().atZone(t.createdAt().getTimeZone().toZoneId());
                    ZonedDateTime updated = t.updatedAt().toInstant().atZone(t.updatedAt().getTimeZone().toZoneId());

                    String jsonObject = String.format("""
                        {
                        "id": "%d",
                        "description": "%s",
                        "status": "%s",
                        "createdAt": "%s",
                        "updatedAt": "%s",
                        }
                        
                        """, t.getId(), t.getDescription(), created.format(DateTimeFormatter.ISO_OFFSET_DATE_TIME), updated.format(DateTimeFormatter.ISO_OFFSET_DATE_TIME));
                    bw.write(jsonObject);
                    System.out.println("Success!");
                }
            } catch (IOException e){
                e.printStackTrace();
            }
        }
    }

}
