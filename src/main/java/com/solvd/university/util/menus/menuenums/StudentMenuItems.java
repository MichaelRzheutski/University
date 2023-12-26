package com.solvd.university.util.menus.menuenums;

public enum StudentMenuItems {
    UNIVERSITY_DEPARTMENTS("Показать список кафедр"),
    UNIVERSITY_ENROLLMENT_RESULTS("Показать результаты вступительной кампании"),
    UNIVERSITY_STUDENTS("Показать список студентов"),
    UNIVERSITY_LECTURERS("Показать список преподавателей"),
    UNIVERSITY_SUBJECT_LIST("Показать список предметов");

    private final String studentMenuItem;

    StudentMenuItems(String studentMenuItem) {
        this.studentMenuItem = studentMenuItem;
    }

    public String getStudentMenuItem() {
        return studentMenuItem;
    }

    @Override
    public String toString() {
        return studentMenuItem;
    }
}
