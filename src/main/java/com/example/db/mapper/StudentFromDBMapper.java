package com.example.db.mapper;

import com.example.db.models.StudentsEntity;
import com.example.models.Student;

public class StudentFromDBMapper {

    public static Student map(Student student) {
        return new Student(student.getName(),
                student.getGroup(),
                student.getIsInRetake(),
                student.getIsHasSuspiciousCoincidences());
    }

}
