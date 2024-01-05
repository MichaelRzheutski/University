package com.solvd.university.persistence;

import com.solvd.university.domain.Building;
import com.solvd.university.domain.Course;

import java.util.List;

public interface CourseRepository {
    void create(Course course);

    Course findById();

    List<Building> findAll();

    void update(Course course);

    void deleteById();

    Long countOfEntries();
}
