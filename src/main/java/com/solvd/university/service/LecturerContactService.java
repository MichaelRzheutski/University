package com.solvd.university.service;

import com.solvd.university.domain.Lecturer;
import com.solvd.university.util.menus.enums.ParserSelectors;

public interface LecturerContactService {
    void createLecturerContact(Lecturer lecturer, ParserSelectors consoleSelector);
}
