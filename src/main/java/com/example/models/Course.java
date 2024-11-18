package com.example.models;

import java.util.ArrayList;
import java.util.stream.Stream;

public class Course {
    public static String nameCourse;
    private final String totalStudents;
    private final String totalGroupsOfStudents;
    public static ArrayList<String> maxScores;
    public static ArrayList<Stream<String>> realScores;

    public Course(String totalStudents, String totalGroupsOfStudents) {
        this.totalStudents = totalStudents;
        this.totalGroupsOfStudents = totalGroupsOfStudents;
    }

    @Override
    public String toString() {
        return String.format("Студент - %s, принадлежащий группе %s, имеет статистику за прохождение курса: ",
                this.totalStudents, this.totalGroupsOfStudents);
    }

}