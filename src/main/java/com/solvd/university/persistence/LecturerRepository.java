package com.solvd.university.persistence;

import java.util.List;

public interface LecturerRepository {
    void create(LecturerRepository lecturerRepository);

    LecturerRepository findById();

    List<LecturerRepository> findAll();

    void update(LecturerRepository lecturerRepository);

    void deleteById();

    Long countOfEntries();
}
