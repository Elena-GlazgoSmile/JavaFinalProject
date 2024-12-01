package com.example.db.models;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

@DatabaseTable(tableName = "Students")
public class StudentsEntity {
    public static final String NAME_COLUMN = "name";
    @DatabaseField(generatedId = true)
    private long StudentID;

    @DatabaseField(canBeNull = false)
    private String name;

    @DatabaseField()
    private String StudentGroup;

    @DatabaseField()
    private String IsInRetake;

    public StudentsEntity() { }

    public StudentsEntity(String name, String studentGroup, String IsIn) {
        this.name = name;
        StudentGroup = studentGroup;
        IsInRetake = IsIn;
    }

    @Override
    public String toString() {
        return "Students{" +
                "StudentID=" + StudentID +
                ", name='" + name + '\'' +
                ", StudentGroup=" + StudentGroup +
                ", IsInRetakeGroup=" + IsInRetake +
                "}";


    }
}
