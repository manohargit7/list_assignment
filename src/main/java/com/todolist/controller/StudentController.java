package com.todolist.controller;
import com.todolist.payload.StudentDto;
import com.todolist.service.StudentService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/students")
public class StudentController {
    private StudentService studentService;

    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }
    //http://localhost:8080/api/students
    @PostMapping
    public ResponseEntity<StudentDto>createStudent(@RequestBody StudentDto studentDto){
         StudentDto dto = studentService.createStudent(studentDto);
         return  new ResponseEntity<>(dto, HttpStatus.CREATED);
    }
    @GetMapping
    public List<StudentDto> getAllStudents(){
       return studentService.getAllStudents();
    }

    //http://localhost:8080/api/students/1
    @PutMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<StudentDto> updateStudent(@RequestBody StudentDto studentDto,
                                              @PathVariable(name = "id") long id){
        StudentDto studentResponse = studentService.updateStudent(studentDto, id);
        return new ResponseEntity<>(studentResponse, HttpStatus.OK);
    }
    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<String>deleteStudentById( @PathVariable(name = "id") long id){
        studentService.deleteStudentById(id);
        return new ResponseEntity<String>("Student Id Deleted!!", HttpStatus.OK);
    }
}
