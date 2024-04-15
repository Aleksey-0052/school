package hogwarts.school.service;

import hogwarts.school.exception.EntityNotFoundException;
import hogwarts.school.model.Faculty;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class FacultyServiceImpl implements FacultyService {

    private final Map<Long, Faculty> faculties = new HashMap<>();
    private static Long idCounter = 0L;


    @Override
    public Faculty create(Faculty faculty) {
        faculty.setId(++idCounter);
        faculties.put(idCounter, faculty);
        return faculty;
    }

    @Override
    public Faculty edit(Faculty faculty) {
        if (faculties.containsKey(faculty.getId())) {
            faculties.put(faculty.getId(), faculty);
            return faculty;
        }

        throw new EntityNotFoundException("Мапа не содержит факультет c таким ключом");
    }

    @Override
    public Faculty delete(Long id) {
        if (faculties.containsKey(id)) return faculties.remove(id);
        throw new EntityNotFoundException("Мапа не содержит факультет c таким ключом");
    }

    @Override
    public Faculty find(Long id) {
        if (faculties.containsKey(id)) return faculties.get(id);
        throw new EntityNotFoundException("Мапа не содержит факультет c таким ключом");
    }

    @Override
    public Collection<Faculty> getAllFaculties() {
        return faculties.values();
    }

    @Override
    public Collection<Faculty> getFacultiesByColor(String color) {
        return faculties.values().stream()
                .filter(faculty -> faculty.getColor().equals(color))
                .collect(Collectors.toList());
    }
}
