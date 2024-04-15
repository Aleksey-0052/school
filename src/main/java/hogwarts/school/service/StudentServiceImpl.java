package hogwarts.school.service;

import hogwarts.school.exception.EntityNotFoundException;
import hogwarts.school.model.Faculty;
import hogwarts.school.model.Student;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class StudentServiceImpl implements StudentService {

    private Map<Long, Student> students = new HashMap<>();
    private static Long idCounter = 0L;


    @Override
    public Student create(Student student) {
        student.setId(++idCounter);
        students.put(idCounter, student);
        return student;
    }

    // редактируем данные студента (имя и/или возраст)
    @Override
    public Student edit(Student student) {
        if (students.containsKey(student.getId())) {
            students.put(student.getId(), student);
            return student;
        }

        throw new EntityNotFoundException("Мапа не содержит студента под таким ключом");
    }

    @Override
    public Student delete(Long id) {
        if (students.containsKey(id)) return students.remove(id);
        throw new EntityNotFoundException("Мапа не содержит студента c таким ключом");
    }

    @Override
    public Student find(Long id) {
        if (students.containsKey(id)) return students.get(id);
        throw new EntityNotFoundException("Мапа не содержит студента c таким ключом");
    }

    @Override
    public Collection<Student> getAllStudents() {
        return students.values();
    }

    @Override
    public Collection<Student> getStudentsByAge(Integer age) {
        return students.values().stream()
                .filter(student -> student.getAge().equals(age))
                .collect(Collectors.toList());
    }
}
