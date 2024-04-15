package hogwarts.school.service;

import hogwarts.school.model.Student;

import java.util.Collection;

public interface StudentService {

    Student create(Student student);
    Student edit(Student student);
    Student delete(Long id);
    Student find(Long id);
    Collection<Student> getAllStudents();
    Collection<Student> getStudentsByAge(Integer age);
}
