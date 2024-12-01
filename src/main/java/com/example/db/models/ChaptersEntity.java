package com.example.db.models;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

@DatabaseTable(tableName = "Chapters")

public class ChaptersEntity {
    public static final String NAME_COLUMN = "name";

    @DatabaseField(generatedId = true)
    private long numberOfChapter;

    @DatabaseField(canBeNull = false)
    private String nameOfChapter;

    public ChaptersEntity() { }

    public ChaptersEntity(String name) {
        this.nameOfChapter = name;
    }


    public String toString() {
        return "Chapters{" +
                "numberOfChapter=" + numberOfChapter +
                "nameOfChapter='" + nameOfChapter +
                "}";
    }
}
