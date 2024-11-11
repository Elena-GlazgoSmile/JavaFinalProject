package com.example.models;

public class Student {
    private final String nameSurname;
    private final String groupNumber;
    private final String isInRetakeGroup;
    public static int totalCountStudents;

    public Student(String nameSurname, String groupNumber, String isInRetakeGroup) {
        this.nameSurname = nameSurname;
        this.groupNumber = groupNumber;
        this.isInRetakeGroup = isInRetakeGroup;
    }

    @Override
    public String toString() {
        return String.format("Студент - %s принадлежит к группе: %s, при этом пересдачи - %s",
                this.nameSurname, this.groupNumber, this.isInRetakeGroup);
    }
}

