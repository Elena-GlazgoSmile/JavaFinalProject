package com.example.db.models;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

@DatabaseTable(tableName = "Student")
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

    @DatabaseField()
    private String IsSusp;

    public StudentsEntity() { }

    public StudentsEntity(String name, String studentGroup, String IsIn, String IsSusp) {
        this.name = name;
        StudentGroup = studentGroup;
        IsInRetake = IsIn;
        this.IsSusp = IsSusp;
    }

    public String getName() {
        return name;
    }

    public String getGroup() {
        return StudentGroup;
    }

    public String getIsInRetake() {
        return IsInRetake;
    }

    public String getIsSusp() {
        return IsSusp;
    }

    @Override
    public String toString() {
        return "Students{" +
                "StudentID=" + StudentID +
                ", name='" + name + '\'' +
                ", StudentGroup=" + StudentGroup +
                ", IsInRetakeGroup=" + IsInRetake +
                ", getIsSusp=" + IsSusp +
                "}";


    }
}
