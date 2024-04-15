package hogwarts.school.controller;


import hogwarts.school.model.Student;
import hogwarts.school.model.Student;
import hogwarts.school.service.FacultyService;
import hogwarts.school.service.StudentService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@RestController
@RequestMapping("student")
@Tag(name = "API для работы со студентами")
public class StudentController {

    private final StudentService service;

    public StudentController(StudentService service) {
        this.service = service;
    }

    @PostMapping
    @Operation(summary = "Создание студента" )
    public Student create(@RequestBody Student student) {
        return service.create(student);
        // необходимо использовать аннотацию @RequestBody org.springframework, но не одноименную аннотацию i.o. swagger
    }

    @PutMapping
    @Operation(summary = "Обновление студента" )
    public Student edit(@RequestBody Student student) {
        return service.edit(student);
    }

    @DeleteMapping("{id}")
    @Operation(summary = "Удаление студента" )
    public Student delete(@PathVariable Long id) {
        return service.delete(id);
    }

    @GetMapping("{id}")
    @Operation(summary = "Получение студента по id" )
    public Student get(@PathVariable ("id") Long id) {
        return service.find(id);
    }

    @GetMapping("all")
    @Operation(summary = "Получение всех студентов" )
    public Collection<Student> getAllStudents() {
        return service.getAllStudents();
    }

    @GetMapping("get-by-{age}")
    @Operation(summary = "Получение студентов по возрасту" )
    public Collection<Student> getStudentsByAge(@PathVariable ("age") Integer age) {
        return service.getStudentsByAge(age);
    }
}
