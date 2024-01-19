package com.solvd.university.util.menus.menuenums;

public enum StudentMenuItems {
    STUDENT_SHOW_ALL_STUDENT_SUBJECTS_PROXY("Показать список всех студентов и их предметов (Proxy)"),
    STUDENT_SHOW_ALL_STUDENT_SUBJECTS("Показать список всех студентов и их предметов"),
    STUDENT_SHOW_STUDENT_SUBJECTS("Показать список всех предметов студента"),
    STUDENT_SHOW_GRADES("Посмотрть успеваемость студента"),
    STUDENT_TAKE_EXAM("Сдать экзамен по предметам");

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
