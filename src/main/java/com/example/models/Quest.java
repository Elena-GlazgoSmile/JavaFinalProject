package com.example.models;

public class Quest {
    private final String questName;
    private final String typeOfQuest;
    private final int questNumber;
    public static int questCounter;

    public Quest(String nameQuest, String type, int questNumber) {
        this.questName = nameQuest;
        this.typeOfQuest = type;
        this.questNumber = questNumber;
    }

    @Override
    public String toString() {
        return String.format("Задача %s %s принадлежит к разделу - %s",
                this.questNumber, this.questName, this.typeOfQuest);
    }
}
