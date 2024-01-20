package com.solvd.university.service;

import com.solvd.university.domain.Lecturer;
import com.solvd.university.util.menus.enums.XmlConsoleSelectors;

public interface LecturerService {
    void printFullLecturerInfo();

    void employLecturer(XmlConsoleSelectors xmlConsoleSelector);

    Lecturer findLecturer();

    void editLecturerInfo();

    void dismissLecturerById();

    void printNumberOfEntries();
}
