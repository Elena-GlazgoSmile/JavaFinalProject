package com.example.parser;

import com.example.models.*;

import java.io.*;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.*;
import java.util.stream.Stream;

public class TableParser {
    public static ArrayList<String> getStatisticsFromCsv(String path) throws IOException {
        Scanner sc = getScanner(path);
        for (int i = 0; i<3; i++) {
            sc.nextLine();
        }
        ArrayList<String> student = new ArrayList<>();
        while (sc.hasNextLine()) {
            String[] studentsParts = sc.nextLine().split(";");
            student.add(0, studentsParts[0]);
        }
        return student;
    }

    public static ArrayList<String> SplitSurname(String path) throws IOException {
        Scanner sc = getScanner(path);
        ArrayList<String> surname = new ArrayList<>();
        for (int i = 0; i<3; i++) {
            sc.nextLine();
        }
        ArrayList<String> student = new ArrayList<>();
        while (sc.hasNextLine()) {
            String[] studentsParts = sc.nextLine().split(";");
            student.add(0, studentsParts[0]);
        }

        for (String val : student) {
            String[] v = val.split(" ");
            surname.add(v[0]);
        }
        return surname;
    }

    public static ArrayList<String> SplitName(String path) throws IOException {
        Scanner sc = getScanner(path);
        ArrayList<String> surnames = SplitSurname("rowData/basicprogramming_2.csv");
        ArrayList<String> name = new ArrayList<>();
        for (int i = 0; i<3; i++) {
            sc.nextLine();
        }
        ArrayList<String> student = new ArrayList<>();
        while (sc.hasNextLine()) {
            String[] studentsParts = sc.nextLine().split(";");
            student.add(0, studentsParts[0]);
        }

        int i = 0;
        for (String val : student) {
            val = val.replace(surnames.get(i), "");
            String[] v = val.split(" ");
            name.add(v[0]);
            i++;
        }
        return name;
    }

    private static Scanner getScanner(String path) throws IOException {
        return new Scanner(new File(path));
    }

    public static ArrayList<String> getHeaders(String path) throws IOException {
        Scanner sc = getScanner(path);
        String[] headers = sc.nextLine().split(";");
        return mapChapters(headers);
    }

    private static ArrayList<String> mapChapters(String[] headers) {
        ArrayList<String> chapters = new ArrayList<String>();
        for (int i = 8; i < headers.length; i+=1) {
            chapters.add(headers[i].replace(",", ""));
        }
        return chapters;
    }

    public static ArrayList<String> getGroup(String path) throws IOException {
        Scanner sc = getScanner(path);
        for (int i = 0; i<3; i++) {
            sc.nextLine();
        }
        ArrayList<String> groups = new ArrayList<>();
        while (sc.hasNextLine()) {
            String[] studentsParts = sc.nextLine().split(";");
            groups.add(0, studentsParts[1]);
        }
        return groups;
    }

    public static ArrayList<String> getRetakeGroup(String path) throws IOException {
        Scanner sc = getScanner(path);
        for (int i = 0; i<3; i++) {
            sc.nextLine();
        }
        ArrayList<String> regroups = new ArrayList<>();
        while (sc.hasNextLine()) {
            String[] studentsParts = sc.nextLine().split(";");
            if (studentsParts[1].contains("Пересдачи")) {
                regroups.add("Есть");
            }
            else {
                regroups.add("Нет");
            }
        }
        return regroups;
    }

    public static ArrayList<String> getNamesQuests(String path) throws IOException {
        Scanner sc = getScanner(path);
        sc.nextLine();
        String[] headers = sc.nextLine().split(";");
        ArrayList<String> quest = new ArrayList<String>();
        for (int i = 10; i < headers.length; i+=1) {
            quest.add(headers[i].replace(",", ""));
        }
        return quest;
    }

    public static ArrayList<String> getTypeQuests(String path) throws IOException {
        Scanner sc = getScanner(path);
        sc.nextLine();
        String[] headers = sc.nextLine().split(";");
        ArrayList<String> quest = new ArrayList<String>();
        ArrayList<String> types = new ArrayList<String>();
        for (int i = 10; i < headers.length; i+=1) {
            quest.add(headers[i].replace(",", ""));
        }
        for (String val : quest) {
            if (val.contains("Упр:")){
                types.add("Упражнение");
            }
            if (val.contains("ДЗ:")){
                types.add("Домашнее задание");
            }
            if (val.contains("Доп")){
                types.add("Дополнительное задание");
            }
            if (val.contains("Акт")){
                types.add("Активность");
            }
        }
        return types;
    }

    public static ArrayList<Student> mapStudent(ArrayList<String> nameSurname,
                                      ArrayList<String> group, ArrayList<String> isRe) {
        ArrayList<Student> students = new ArrayList<>();
        for (int i = 0; i < nameSurname.toArray().length; i++) {
            Student student = new Student(nameSurname.get(i), group.get(i), isRe.get(i));
            students.add(student);
        }

        return students;
    }
    public static ArrayList<Chapter> mapChapters(ArrayList<String> chapters) {
        ArrayList<Chapter> chapter = new ArrayList<>();
        for (int i = 0; i < chapters.toArray().length; i++) {
            Chapter chap = new Chapter(chapters.get(i), i+1);
            chapter.add(chap);
        }

        return chapter;
    }

    public static ArrayList<Quest> mapQuests(ArrayList<String> nameQuest, ArrayList<String> type) {
        ArrayList<Quest> quest = new ArrayList<>();
        for (int i = 0; i < type.toArray().length; i++) {
            Quest q = new Quest(nameQuest.get(i), type.get(i), i+1);
            quest.add(q);
        }

        return quest;
    }

    public static String getFileName(String path) throws IOException {
        String[] parts = Paths.get(path).getFileName().toString().split("\\.");
        return parts[0];
    }

    public static ArrayList<String> getMaxScores(String path) throws IOException {
        Scanner sc = getScanner(path);
        sc.nextLine();
        sc.nextLine();
        String[] headers = sc.nextLine().split(";");
        ArrayList<String> maxScores = new ArrayList<String>();
        for (int i = 2; i < headers.length; i+=1) {
            maxScores.add(headers[i].replace(",", ""));
        }
        return maxScores;
    }

    public static ArrayList<String> getRealScores(String path) throws IOException {
        Scanner sc = getScanner(path);
        String[] headers = sc.nextLine().split(";");
        sc.nextLine();
        sc.nextLine();
        ArrayList<ArrayList<String>> realScores = new ArrayList<>();
        ArrayList<String> realAllScores = new ArrayList<>();
        while(sc.hasNextLine()) {
            headers = sc.nextLine().split("\n");
            for (int j = 0; j < headers.length; j++) {
                realAllScores.add(headers[j].replace(",", ""));
            }

        }
        realScores.add(realAllScores);
        sc.close();
        return realAllScores;

    }

    public static ArrayList<Stream<String>> realScores(String path) throws IOException {
        ArrayList<String> array = getRealScores(path);
        ArrayList<Stream<String>> scores = new ArrayList<>();

        for (String s : array) {
            scores.add(Arrays.stream(s.split(";")).skip(2));
        }

        return scores;
    }
/*
    public static ArrayList<String> realScores(String path) throws IOException {
        ArrayList<String> array = getRealScores(path);
        ArrayList<String> scores = new ArrayList<>();

        for (String s : array) {
            scores.add(Arrays.toString((s.split(";"))));
        }

        return scores;
    }
*/
    public static ArrayList<Course> mapCourse(ArrayList<String> students, ArrayList<String> groups) throws IOException {
        ArrayList<Course> course = new ArrayList<>();
        for (int i = 0; i<students.size(); i++) {
            Course chap = new Course(students.get(i), groups.get(i));
            course.add(chap);
        }

        return course;
    }

}
