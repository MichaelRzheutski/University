package com.solvd.university.service;

import com.solvd.university.domain.Student;
import com.solvd.university.util.menus.enums.XmlConsoleSelectors;

public interface StudentContactService {
    void createStudentContact(Student student, XmlConsoleSelectors consoleSelector);
}
