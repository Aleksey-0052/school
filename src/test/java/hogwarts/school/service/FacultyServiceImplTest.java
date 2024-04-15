package hogwarts.school.service;

import hogwarts.school.exception.EntityNotFoundException;
import hogwarts.school.model.Faculty;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.*;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.*;

class FacultyServiceImplTest {

    FacultyService facultyService;

    private Faculty faculty1;
    private Faculty faculty2;
    private Faculty faculty3;
    private Faculty faculty4;

    @BeforeEach
    public void setUp() {
        facultyService = new FacultyServiceImpl();

        faculty1 = new Faculty(1L,"Исторический", "Красный");
        faculty2 = new Faculty(2L,"Географический", "Голубой");
        faculty3 = new Faculty(3L,"Математический", "Красный");
        faculty4 = new Faculty(4L,"Физический", "Голубой");
    }

    @Test
    void testCreateFaculty_Success() {

        Faculty expected = new Faculty(5L,"Экономический", "Голубой");
        Faculty actual = facultyService.create(expected);
        assertEquals(expected, actual);
    }

    @Test
    void testEditFaculty_Success() {

        facultyService.create(faculty1);
        facultyService.create(faculty2);
        facultyService.create(faculty3);

        Faculty expected = new Faculty(2L,"Спортивный", "Белый");
        Faculty actual = facultyService.edit(expected);
        assertEquals(expected, actual);

    }

    @Test
    void testEditFaculty_ThrowsEntityNotFoundException() {
        facultyService.create(faculty1);
        facultyService.create(faculty2);

        Faculty faculty = new Faculty(3L,"Спортивный", "Белый");

        assertThrows(EntityNotFoundException.class, () -> {
            facultyService.edit(faculty);
        });
    }


    @Test
    void testDeleteFaculty_Success() {

        Faculty expected = facultyService.create(faculty1);
        Faculty actual = facultyService.delete(1L);

        assertEquals(expected, actual);
    }

    @Test
    void testDeleteFaculty_ThrowsEntityNotFoundException() {

        facultyService.create(faculty1);
        facultyService.create(faculty2);

        assertThrows(EntityNotFoundException.class, () -> {
            facultyService.delete(3L);
        });
    }

    @Test
    void testFindFaculty_Success() {

        Faculty expected1 = facultyService.create(faculty1);
        Faculty expected2 = facultyService.create(faculty2);

        Faculty actual1 = facultyService.find(1L);
        Faculty actual2 = facultyService.find(2L);

        assertEquals(expected1, actual1);
        assertEquals(expected2, actual2);
    }

    @Test
    void testFindFaculty_ThrowsEntityNotFoundException() {

        facultyService.create(faculty1);
        facultyService.create(faculty2);

        assertThrows(EntityNotFoundException.class, () -> {
            facultyService.find(0L);
        });
    }

    @Test
    void getAllFaculties() {

        Map<Long, Faculty> faculties = new HashMap<>();
        faculties.put(1L, faculty1);
        faculties.put(2L, faculty2);
        faculties.put(3L, faculty3);
        faculties.put(4L, faculty4);
        Collection<Faculty> expected = faculties.values();

        facultyService.create(faculty1);
        facultyService.create(faculty2);
        facultyService.create(faculty3);
        facultyService.create(faculty4);
        Collection<Faculty> actual = facultyService.getAllFaculties();

        assertEquals(expected.size(), actual.size());
//        assertEquals(expected, actual);
        assertTrue(actual.containsAll(expected));

    }

    @Test
    void testGetFacultiesByColor_Success() {

        String color = "Красный";

        Collection<Faculty> expected = List.of(faculty1, faculty3);

        facultyService.create(faculty1);
        facultyService.create(faculty2);
        facultyService.create(faculty3);
        facultyService.create(faculty4);
        Collection<Faculty> actual = facultyService.getFacultiesByColor(color);

        assertEquals(2, actual.size());             // проверка, что только два факультета
        assertEquals(expected, actual);                      // проверка, что список факультетов совпадает с ожидаемым

    }
}