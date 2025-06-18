package org.example.exercicestudent.services;

import org.example.exercicestudent.models.Student;
import org.example.exercicestudent.repositories.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class MySqlStudentService implements StudentService {

    @Autowired
    private StudentRepository studentRepository;

    public MySqlStudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
        Student student1 = Student.builder().id(UUID.randomUUID()).firstname("John").lastname("Doe").age(18).email("example@gmail.com").build();
        Student student2 = Student.builder().id(UUID.randomUUID()).firstname("Nelson").lastname("Sans Chaise").email("sans@chaise.io").age(5).build();
        Student student3 = Student.builder().id(UUID.randomUUID()).firstname("Salimou").lastname("Le filou").email("filou@salimou.netou").age(8).build();
        studentRepository.save(student1);
        studentRepository.save(student2);
        studentRepository.save(student3);
    }

    @Override
    public List<Student> getStudents() {
        return studentRepository.findAll();
    }

    @Override
    public Student getStudent(UUID id) {
        return studentRepository.findById(id).get();
    }

    @Override
    public Student addStudent(Student student) {
        student.setId(UUID.randomUUID());
        return studentRepository.save(student);
    }

    @Override
    public Student updateStudent(Student student) {
        return studentRepository.save(student);
    }

    @Override
    public List<Student> getStudentsByName(List<Student> studentList, String name) {
        return getStudents().stream().filter(s-> s.getFirstname().startsWith(name) || s.getLastname().startsWith(name)).toList();
    }

    @Override
    public void deleteStudent(UUID id) {
        studentRepository.deleteById(id);
    }


}
