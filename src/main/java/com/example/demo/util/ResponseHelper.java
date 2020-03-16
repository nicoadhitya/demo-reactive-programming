package com.example.demo.util;

import com.example.demo.entity.Grade;
import com.example.demo.entity.Student;
import com.example.demo.response.GoodGradeResponse;
import com.example.demo.response.StudentResponse;

public class ResponseHelper {
  public static StudentResponse convertToStudentResponse(Student student) {
    return StudentResponse.builder()
        .nim(student.getNim())
        .name(student.getName())
        .gender(student.getGender())
        .grades(student.getGrades())
        .build();
  }

  public static GoodGradeResponse convertToGradeResponse(Grade grade) {
    return GoodGradeResponse.builder()
        .course(grade.getCourse())
        .semester(grade.getSemester())
        .score(grade.getScore())
        .build();
  }
}
