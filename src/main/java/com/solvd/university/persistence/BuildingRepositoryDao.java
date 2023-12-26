package com.solvd.university.persistence;

import com.solvd.university.domain.Building;

import java.util.List;

public interface BuildingRepositoryDao {
    void create(Building building);

    void findById();

    List<Building> findAll();

    void update(Building building);

    void deleteById();

    Long countOfEntries();
}
