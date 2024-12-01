package com.example.models;

import java.util.ArrayList;

public class Student {
    private final String nameSurname;
    private final String groupNumber;
    private final String isInRetakeGroup;
    public static int totalCountStudents;
    public static ArrayList<String> isHasSuspiciousCoincidences;

    public Student(String nameSurname, String groupNumber, String isInRetakeGroup) {
        this.nameSurname = nameSurname;
        this.groupNumber = groupNumber;
        this.isInRetakeGroup = isInRetakeGroup;
    }

    public String getName() {
        return nameSurname;
    }

    public String getGroup() {
        return groupNumber;
    }

    @Override
    public String toString() {
        return String.format("Студент - %s принадлежит к группе: %s, при этом пересдачи - %s",
                this.nameSurname, this.groupNumber, this.isInRetakeGroup);
    }

}

