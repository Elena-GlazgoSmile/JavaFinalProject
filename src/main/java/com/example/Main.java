package com.example;

import com.example.models.Chapter;
import com.example.models.Course;
import com.example.models.Quest;
import com.example.models.Student;
import com.example.parser.TableParser;

import java.io.IOException;
import java.util.*;

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

    public static void main(String[] args) throws IOException {
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
*/
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
