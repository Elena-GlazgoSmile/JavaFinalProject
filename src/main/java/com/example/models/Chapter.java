package com.example.models;

public class Chapter {
    private final String nameOfChapter;
    private final int numberOfChapter;
    public static int countOfChapters;


    public Chapter(String chapters, int numberOfChapter) {
        this.nameOfChapter = chapters;
        this.numberOfChapter = numberOfChapter;
    }

    @Override
    public String toString() {
        return String.format("Глава %s - %s", this.numberOfChapter, this.nameOfChapter);
    }
}

