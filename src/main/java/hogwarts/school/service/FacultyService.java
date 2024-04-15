package hogwarts.school.service;

import hogwarts.school.model.Faculty;

import java.util.Collection;

public interface FacultyService {

    Faculty create(Faculty faculty);
    Faculty edit(Faculty faculty);
    Faculty delete(Long id);
    Faculty find(Long id);
    Collection<Faculty> getAllFaculties();
    Collection<Faculty> getFacultiesByColor(String color);
}
