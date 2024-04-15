package hogwarts.school.controller;

import hogwarts.school.model.Faculty;
import hogwarts.school.service.FacultyService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
@RestController
@RequestMapping("faculty")
@Tag(name = "API для работы с факультетами")
public class FacultyController {

    private final FacultyService service;

    public FacultyController(FacultyService service) {
        this.service = service;
    }

    @PostMapping
    @Operation(summary = "Создание факультета" )
    public Faculty create(@RequestBody Faculty faculty) {
        return service.create(faculty);
        // необходимо использовать аннотацию @RequestBody org.springframework, но не одноименную аннотацию i.o. swagger
    }

    @PutMapping
    @Operation(summary = "Обновление факультета" )
    public Faculty edit(@RequestBody Faculty faculty) {
        return service.edit(faculty);
    }

    @DeleteMapping("{id}")
    @Operation(summary = "Удаление факультета" )
    public Faculty delete(@PathVariable Long id) {
        return service.delete(id);
    }

    @GetMapping("{id}")
    @Operation(summary = "Получение факультета по id" )
    public Faculty get(@PathVariable Long id) {
        return service.find(id);
    }

    @GetMapping("all")
    @Operation(summary = "Получение всех факультетов" )
    public Collection<Faculty> getAllFaculties() {
        return service.getAllFaculties();
    }

    @GetMapping("get-by-color")
    @Operation(summary = "Получение факультетов по цвету" )
    public Collection<Faculty> getStudentsByAge(@RequestParam String color) {
        return service.getFacultiesByColor(color);
    }

}
