package com.example.db.models;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

@DatabaseTable(tableName = "Quests")
public class QuestsEntity {
    public static final String NAME_COLUMN = "name";

    @DatabaseField(generatedId = true)
    private long questNumber;

    @DatabaseField(canBeNull = false)
    private String typeOfQuest;

    @DatabaseField(canBeNull = false)
    private String questName;

    public QuestsEntity() { }

    public QuestsEntity(String type, String questName) {
        this.typeOfQuest = type;
        this.questName = questName;
    }


    public String toString() {
        return "Quests{" +
                "questNumber=" + questNumber +
                "typeOfQuest='" + typeOfQuest + '\'' +
                "questName='" + questName +
                "}";
    }
}
