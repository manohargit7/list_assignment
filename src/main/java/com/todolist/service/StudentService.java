package com.todolist.service;

import com.todolist.payload.StudentDto;

import java.util.List;

public interface StudentService {
      StudentDto  createStudent(StudentDto studentDto);

      List<StudentDto> getAllStudents();
      StudentDto updateStudent(StudentDto studentDto, long id);

      void deleteStudentById(long id);
}
