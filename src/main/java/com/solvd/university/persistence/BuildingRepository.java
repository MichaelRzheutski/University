package com.solvd.university.persistence;

import com.solvd.university.domain.Building;

import java.util.List;

public interface BuildingRepository {
    void create(Building building);

    Building findById();

    List<Building> findAll();

    void update(Building student);

    void deleteById();

    Long countOfEntries();
}
