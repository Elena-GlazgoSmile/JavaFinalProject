package com.example.models;

import java.util.ArrayList;

public class Student {
    private final String nameSurname;
    private final String groupNumber;
    private final String isInRetakeGroup;
    public static int totalCountStudents;
    private static String isHasSuspiciousCoincidences;

    public Student(String nameSurname, String groupNumber, String isInRetakeGroup, String isHasSuspiciousCoincidences) {
        this.nameSurname = nameSurname;
        this.groupNumber = groupNumber;
        this.isInRetakeGroup = isInRetakeGroup;
        Student.isHasSuspiciousCoincidences = isHasSuspiciousCoincidences;
    }

    public String getName() {
        return nameSurname;
    }

    public String getGroup() {
        return groupNumber;
    }

    public String getIsInRetake() {
        return isInRetakeGroup;
    }

    public String getIsHasSuspiciousCoincidences() {
        return isHasSuspiciousCoincidences;
    }

    @Override
    public String toString() {
        return String.format("Студент - %s принадлежит к группе: %s, при этом пересдачи - %s, из совпадений: %s",
                this.nameSurname, this.groupNumber, this.isInRetakeGroup, this.isHasSuspiciousCoincidences);
    }

}

