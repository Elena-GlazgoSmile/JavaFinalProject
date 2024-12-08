package com.example.db.models;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

import java.util.ArrayList;

@DatabaseTable(tableName = "Course")
public class CourseEntity {
    public static final String NAME_COLUMN = "name";

    @DatabaseField(canBeNull = false)
    private String totalStudents;

    @DatabaseField()
    private String totalGroupsOfStudents;

    @DatabaseField()
    private String maxScores;

    @DatabaseField()
    private String realScores;

    public CourseEntity() { }

    public CourseEntity(String totalStudents, String totalGroupsOfStudents,
                        String maxScores, String realScores) {
        this.totalStudents = totalStudents;
        this.totalGroupsOfStudents = totalGroupsOfStudents;
        this.maxScores = maxScores;
        this.realScores = realScores;
    }

    @Override
    public String toString() {
        return "Course{" +
                "totalStudents=" + totalStudents +
                ", totalGroupsOfStudents'" + totalGroupsOfStudents + '\'' +
                ", maxScores=" + maxScores +
                ", realScores=" + realScores +
                "}";


    }
}
