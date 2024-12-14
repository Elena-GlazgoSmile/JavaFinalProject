package com.example;

import com.example.db.DBORMRepository;
import com.example.db.DBRepository;
import com.example.db.models.StudentsEntity;
import com.example.models.Chapter;
import com.example.models.Course;
import com.example.models.Quest;
import com.example.models.Student;
import com.example.parser.TableParser;
import com.example.visualisation.mapper.ChartOfStudents;
import com.example.visualisation.mapper.StudentRealScores;
import com.example.visualisation.mapper.newChart;
import com.example.vkApi.VKRepository;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import org.jfree.chart.ChartUtils;


import javax.swing.*;
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
    public static ArrayList<String> students = null;
    static ArrayList<String> headers = null;
    static ArrayList<String> group = null;
    public static ArrayList<String> isInRe = null;
    static ArrayList<String> quest = null;
    static ArrayList<String> typesquest = null;
    static ArrayList<Student> allStudents = null;
    static ArrayList<Chapter> allChapters = null;
    static ArrayList<Quest> allQuests = null;
    static ArrayList<Course> allStatisticsInCourse = null;
    static ArrayList<String> Ids = null;
    public static List<Integer> sums = null;
    public static ArrayList<String> lastSuspicious = null;

    public static void main(String[] args) throws Exception {
        getInfo();
/*
        System.out.println("Список всех студентов:");
        System.out.println(students);

 */

/*
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


        System.out.println("Совпадения по баллам среди студентов одной группы, " +
                "исключая совпадения с нулевыми статистиками");
        for (String suspicious : lastSuspicious) {
            System.out.println(suspicious);
        }

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


     ///DBRepository.connect();
     ///DBRepository.createTableStudents();
        DBORMRepository DBO = new DBORMRepository();
        DBO.connect();

        DBO.createTable();
        DBO.saveStudent(students, group, isInRe, lastSuspicious);
        System.out.println(DBO.getStudents());
        System.out.println(DBO.getStudentByName("Ануфриков Максим"));
        DBO.close();

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


/*
        javax.swing.SwingUtilities.invokeLater(() -> {
            newChart suspStudentsChart = new newChart("C# Students by Array of Suspicious Coincidences");
            suspStudentsChart.setExtendedState(JFrame.MAXIMIZED_BOTH);
            suspStudentsChart.setSize(800, 600);
            suspStudentsChart.setLocationRelativeTo(null);
            suspStudentsChart.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            suspStudentsChart.setVisible(true);

        });

        javax.swing.SwingUtilities.invokeLater(() -> {
            ChartOfStudents groups = new ChartOfStudents("Retake Groups or Basic Groups");
            groups.setExtendedState(JFrame.MAXIMIZED_BOTH);
            groups.setSize(800, 600);
            groups.setLocationRelativeTo(null);
            groups.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            groups.setVisible(true);

        });
*/
        SwingUtilities.invokeLater(() -> {
            StudentRealScores example = new StudentRealScores("Реальные баллы у студентов");
            example.setSize(800, 600);
            example.setLocationRelativeTo(null);
            example.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
            example.setVisible(true);
        });


        //System.out.println(StudentFromDBMapper.map(allStudents.get(0)));
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


            allChapters = TableParser.mapChapters(headers);
            allQuests = TableParser.mapQuests(quest, typesquest);
            Course.maxScores = TableParser.getMaxScores("rowData/basicprogramming_2.csv");
            Course.realScores = TableParser.realScores("rowData/basicprogramming_2.csv");
            sums = Course.realScores.stream()
                   .map(stream -> stream
                           .mapToInt(Integer::parseInt)
                           .sum())
                    .collect(Collectors.toList());

            Map<String,String> dictionary = new HashMap<String,String>();
            for (int i = 0; i < sums.size(); i++) {
                for (int j = 0; j < sums.size(); j++) {
                    if (sums.get(i).equals(sums.get(j)) && i!=j && group.get(i).equals(group.get(j)) && !sums.get(i).equals(0)) {
                        dictionary.put(String.format(String.valueOf(i)), String.format(String.valueOf(j)));
                    }
                }
            }



            //System.out.println(i + " " + dictionary.get(String.valueOf(i)));
            ArrayList<String> isSuspicious = new ArrayList<>();
            for (int i = 0; i < sums.size(); i++) {
                if (dictionary.get(String.valueOf(i)) != null) {
                    int num = i + 1 ;
                    int number = Integer.parseInt(dictionary.get(String.valueOf(i))) + 1;
                    isSuspicious.add("Совпадение номера " + num + " c номером " + number);
                } else {
                    isSuspicious.add("Нет совпадений");
                }
            }


            lastSuspicious = isSuspicious;
            allStudents = TableParser.mapStudent(students, group, isInRe, lastSuspicious);
            allStatisticsInCourse = TableParser.mapCourse(students, group);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
