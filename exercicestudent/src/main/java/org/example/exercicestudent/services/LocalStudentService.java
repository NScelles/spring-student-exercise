package org.example.exercicestudent.services;

import org.example.exercicestudent.models.Student;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@Service
public class LocalStudentService implements StudentService {
    private final Map<UUID, Student> students;
    public LocalStudentService(){
        students = new HashMap<>();
        Student student1 = Student.builder().id(UUID.randomUUID()).firstname("John").lastname("Doe").age(18).email("example@gmail.com").build();
        Student student2 = Student.builder().id(UUID.randomUUID()).firstname("Nelson").lastname("Sans Chaise").email("sans@chaise.io").age(5).build();
        Student student3 = Student.builder().id(UUID.randomUUID()).firstname("Salimou").lastname("Le filou").email("filou@salimou.netou").age(8).build();
        students.put(student1.getId(), student1);
        students.put(student2.getId(), student2);
        students.put(student3.getId(), student3);
    }

    public List<Student> getStudents() {
        return students.values().stream().toList();
    }

    public Student getStudent(UUID id) {
        return students.get(id);
    }

    public Student addStudent(Student student) {
        student.setId(UUID.randomUUID());
        students.put(student.getId(), student);
        return student;
    }
    public Student updateStudent(Student student) {
        students.replace(student.getId(), student);
        return student;
    }

    public List<Student> getStudentsByName(List<Student> studentList, String name) {
        if (name != null && !name.isEmpty()) {
            studentList =  studentList.stream().filter(p -> p.getLastname().startsWith(name) || p.getFirstname().startsWith(name)).toList();
        }
        return studentList;
    }

    public void deleteStudent(UUID id) {
        students.remove(id);
    }

}
