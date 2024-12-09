package com.example;

import com.example.db.DBORMRepository;
import com.example.db.DBRepository;
import com.example.models.Chapter;
import com.example.models.Course;
import com.example.models.Quest;
import com.example.models.Student;
import com.example.parser.TableParser;
import com.example.vkApi.VKRepository;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;


import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.sql.SQLException;
import java.util.*;
import java.util.stream.Collectors;

import static com.example.vkApi.VKRepository.getUserCity;
import static com.example.vkApi.VKRepository.searchUsers;

public class Main {
    static ArrayList<String> students = null;
    static ArrayList<String> headers = null;
    static ArrayList<String> group = null;
    static ArrayList<String> isInRe = null;
    static ArrayList<String> quest = null;
    static ArrayList<String> typesquest = null;
    static ArrayList<Student> allStudents = null;
    static ArrayList<Chapter> allChapters = null;
    static ArrayList<Quest> allQuests = null;
    static ArrayList<Course> allStatisticsInCourse = null;
    static ArrayList<String> Ids = null;

    public static void main(String[] args) throws Exception {
        getInfo();
/*
        System.out.println("Список всех студентов:");
        System.out.println(students);



        System.out.println("Список глав:");
        System.out.println(headers);
        System.out.println("Группы");
        System.out.println(group);
        System.out.println("Группы относящиеся к пересдаче");
        System.out.println(isInRe);
        System.out.println("Названия задач");
        System.out.println(quest);
        System.out.println("Типы задач");
        System.out.println(typesquest);

        System.out.println("Поля, принадлежащие классу Student:");
        System.out.println(String.format("Всего студентов: %s", Student.totalCountStudents));
        for (Student val : allStudents)
            System.out.println(val.toString());
        System.out.println("_______");
        System.out.println("Поля, принадлежащие классу Chapter:");
        System.out.println(String.format("Всего глав: %s", Chapter.countOfChapters));
        for (Chapter val : allChapters)
            System.out.println(val.toString());
        System.out.println("_______");
        System.out.println("Поля, принадлежащие классу Quest");

        System.out.println(String.format("Всего задач: %s", Quest.questCounter));

        for (Quest val : allQuests)
            System.out.println(val.toString());

        System.out.println("_______");
        System.out.println("Поля, принадлежащие классу Course:");
        System.out.println("Название курса");
        System.out.println(Course.nameCourse);
        System.out.println("Максимальные значения за задания ulearn");
        System.out.println(Course.maxScores);
        System.out.println("Реальные значения баллов студентов за задания ulearn:");

        for (int i = 0; i < allStatisticsInCourse.size(); i++) {
            System.out.println(allStatisticsInCourse.get(i) + Arrays.toString(Course.realScores.get(i).toArray()));
        }

        VKRepository vkApiClient = new VKRepository();
        List<String> fullNames = students;
        int j = -1;
        for (String val : vkApiClient.getUserIdsByNames(fullNames, 0, 6)) {
            j++;
            System.out.println(String.format("Студент - %s, %s", students.get(j), val));
        }

        try {
            for (int x = 0; x < 10; x++) {
                JsonArray arrayOfUsers = searchUsers(students.get(x));
                for (int i = 0; i < arrayOfUsers.size(); i++) {
                    int ID = arrayOfUsers.get(i).getAsJsonObject().get("id").getAsInt();
                    JsonObject city = getUserCity(ID);
                    if (city != null) System.out.println(String.format("У студента с ID %s, указан город %s",
                            ID, city.get("title").getAsString()));
                    System.out.println(String.format("У студента с ID %s, город не указан", ID));
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        Scanner scanner = new Scanner(new File("C:\\Users\\712\\Desktop\\sqlite\\test.txt"));
        while (scanner.hasNextLine()) {
            System.out.println(scanner.nextLine());
        }
        scanner.close();
*/

     ///DBRepository.connect();
     ///DBRepository.createTableStudents();
        DBORMRepository DBO = new DBORMRepository();
        DBO.connect();

        DBO.createTable();
        DBO.saveStudent(students, group, isInRe);
        System.out.println(DBO.getStudents());
        System.out.println(DBO.getStudentByName("Ануфриков Максим"));
        DBO.close();
/*
        DBO.createTableOfChapters();
        DBO.saveChapters(headers);
        System.out.println(DBO.getChapters());
        DBO.close();

        DBO.createTableOfQuests();
        DBO.saveQuests(typesquest, quest);
        System.out.println(DBO.getQuests());
        DBO.close();

        DBO.createTableOfCourse();
        DBO.saveCourse(students, group, Course.maxScores, Course.realScores);
        System.out.println(DBO.getCourse());
        DBO.close();
*/

    }

    private static void getInfo() {
        try {
            students = TableParser.getStatisticsFromCsv("rowData/basicprogramming_2.csv");
            Collections.reverse(students);
            Student.totalCountStudents = students.size();
            headers = TableParser.getHeaders("rowData/basicprogramming_2.csv");
            headers.removeIf(header -> header.equals(""));
            Chapter.countOfChapters = headers.size();
            group = TableParser.getGroup("rowData/basicprogramming_2.csv");
            Collections.reverse(group);
            isInRe = TableParser.getRetakeGroup("rowData/basicprogramming_2.csv");
            quest = TableParser.getNamesQuests("rowData/basicprogramming_2.csv");
            quest.removeIf(quest -> quest.equals("Сем"));
            quest.removeIf(quest -> quest.equals("Упр"));
            quest.removeIf(quest -> quest.equals("ДЗ"));
            Quest.questCounter = quest.size();
            typesquest = TableParser.getTypeQuests("rowData/basicprogramming_2.csv");
            Course.nameCourse = TableParser.getFileName("rowData/basicprogramming_2.csv");
            allStudents = TableParser.mapStudent(students, group, isInRe);
            allChapters = TableParser.mapChapters(headers);
            allQuests = TableParser.mapQuests(quest, typesquest);
            Course.maxScores = TableParser.getMaxScores("rowData/basicprogramming_2.csv");
            Course.realScores = TableParser.realScores("rowData/basicprogramming_2.csv");
            allStatisticsInCourse = TableParser.mapCourse(students, group);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
