package org.example.exercicestudent.services;

import org.example.exercicestudent.models.Student;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public interface StudentService {
    public List<Student> getStudents();
    public Student getStudent(UUID id);
    public Student addStudent(Student student);
    public Student updateStudent(Student student);
    public List<Student> getStudentsByName(List<Student> studentList, String name);
    public void deleteStudent(UUID id);
}
