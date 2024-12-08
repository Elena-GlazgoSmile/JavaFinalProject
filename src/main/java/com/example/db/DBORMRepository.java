package com.example.db;

import com.example.db.models.ChaptersEntity;
import com.example.db.models.CourseEntity;
import com.example.db.models.QuestsEntity;
import com.example.db.models.StudentsEntity;
import com.example.models.Quest;
import com.example.models.Student;
import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.dao.DaoManager;
import com.j256.ormlite.jdbc.JdbcConnectionSource;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.TableUtils;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class DBORMRepository {
    private final String URL = "jdbc:sqlite:C:\\Users\\712\\Desktop\\sqlite\\test.db";

    private ConnectionSource connectionSource = null;

    private Dao<StudentsEntity, String> STUDENTS_DAO = null;
    private Dao<ChaptersEntity, String> CHAPTERS_DAO = null;
    private Dao<QuestsEntity, String> QUESTS_DAO = null;
    private Dao<CourseEntity, String> COURSE_DAO = null;

    public void connect() throws SQLException {
        connectionSource = new JdbcConnectionSource(URL);

        STUDENTS_DAO = DaoManager.createDao(connectionSource, StudentsEntity.class);
        CHAPTERS_DAO = DaoManager.createDao(connectionSource, ChaptersEntity.class);
        QUESTS_DAO = DaoManager.createDao(connectionSource, QuestsEntity.class);
        COURSE_DAO = DaoManager.createDao(connectionSource, CourseEntity.class);
    }

    public void createTable() throws SQLException {
        TableUtils.createTable(connectionSource, StudentsEntity.class);
    }

    public void saveStudent(ArrayList<String> students,
                            ArrayList<String> group, ArrayList<String> isInRe) throws SQLException {
        for (int i = 0; i < students.size(); i++) {
            STUDENTS_DAO.create(new StudentsEntity(students.get(i), group.get(i), isInRe.get(i)));
        }
    }
    public List<StudentsEntity> getStudents() throws SQLException {
        return STUDENTS_DAO.queryForAll();
    }

    public List<StudentsEntity> getStudentByName(String name) throws SQLException {
        return STUDENTS_DAO.queryBuilder()
                .where()
                .eq(StudentsEntity.NAME_COLUMN, name)
                .query();
    }

    public void close() throws Exception {
        connectionSource.close();
    }

    public void createTableOfChapters() throws SQLException {
        TableUtils.createTable(connectionSource, ChaptersEntity.class);
    }
    public void saveChapters(ArrayList<String> headers) throws SQLException {
        for (int i = 0; i < headers.size(); i++) {
            CHAPTERS_DAO.create(new ChaptersEntity(headers.get(i)));
        }
    }
    public List<ChaptersEntity> getChapters() throws SQLException {
        return CHAPTERS_DAO.queryForAll();
    }

    public void createTableOfQuests() throws SQLException {
        TableUtils.createTable(connectionSource, QuestsEntity.class);
    }

    public void saveQuests(ArrayList<String> typesquest,
                           ArrayList<String> quest) throws SQLException {
        for (int i = 0; i < quest.size(); i++) {
            QUESTS_DAO.create(new QuestsEntity(typesquest.get(i), quest.get(i)));
        }
    }

    public List<QuestsEntity> getQuests() throws SQLException {
        return QUESTS_DAO.queryForAll();
    }

    public void createTableOfCourse() throws SQLException {
        TableUtils.createTable(connectionSource, CourseEntity.class);
    }

    public void saveCourse(ArrayList<String> totalStudents, ArrayList<String> totalGroupsOfStudents,
                           ArrayList<String> maxScores, ArrayList<Stream<String>> realScores) throws SQLException {
        /*
        ArrayList<ArrayList<String>> resultList = (ArrayList<ArrayList<String>>) realScores.stream()
                .map(stream -> stream.collect(Collectors.toCollection(ArrayList::new)));
        */
        List<Integer> sums = realScores.stream()
                .map(stream -> stream
                        .mapToInt(Integer::parseInt)
                        .sum())
                .collect(Collectors.toList());
        for (int i = 0; i < totalStudents.size(); i++) {
            COURSE_DAO.create(new CourseEntity(totalStudents.get(i),
                    totalGroupsOfStudents.get(i),
                    String.valueOf((Integer.parseInt(maxScores.get(0)) +
                            Integer.parseInt(maxScores.get(1)) +
                            Integer.parseInt(maxScores.get(2)))),
                    sums.get(i).toString()));
        }
    }

    public List<CourseEntity> getCourse() throws SQLException {
        return COURSE_DAO.queryForAll();
    }


}
