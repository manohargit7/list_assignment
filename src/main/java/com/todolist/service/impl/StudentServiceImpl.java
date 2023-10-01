package com.todolist.service.impl;
import com.todolist.entity.Student;
import com.todolist.exception.ResourceNotFoundException;
import com.todolist.payload.StudentDto;
import com.todolist.repository.StudentRepository;
import com.todolist.service.StudentService;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class StudentServiceImpl implements StudentService {
    private StudentRepository studentRepository;

    public StudentServiceImpl(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    @Override
    public StudentDto createStudent(StudentDto studentDto) {
        Student student=new Student();
        student.setName(studentDto.getName());
        student.setEmail(studentDto.getEmail());

        Student newStudent=studentRepository.save(student);
        StudentDto dto=new StudentDto();
        dto.setId(newStudent.getId());
        dto.setName(newStudent.getName());
        dto.setEmail(newStudent.getEmail());
        return dto;
    }

    @Override
    public List<StudentDto> getAllStudents() {
       List<Student> students=studentRepository.findAll();
        return  students.stream().map(student -> mapToDto(student)).collect(Collectors.toList());

    }

    @Override
    public StudentDto updateStudent(StudentDto studentDto, long id) {
        Student student=studentRepository.findById(id).orElseThrow(
                ()->new ResourceNotFoundException("Student","Id",id)
        );
        student.setName(studentDto.getName());
        student.setEmail(studentDto.getEmail());

        Student updatedStudent = studentRepository.save(student);
        return mapToDto(updatedStudent);
    }

    @Override
    public void deleteStudentById(long id) {
       Student student= studentRepository.findById(id).orElseThrow(
                ()->new ResourceNotFoundException("student","Id",id)
        );
        studentRepository.deleteById(id);


    }

    StudentDto  mapToDto(Student student) {
            StudentDto dto = new StudentDto();
            dto.setId(student.getId());
            dto.setName(student.getName());
            dto.setEmail(student.getEmail());
            return dto;
        }
}
