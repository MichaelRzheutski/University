package com.solvd.university.util.menus.menuenums;

public enum UniversityMenuItems {
    UNIVERSITY_STUDENT_OPERATIONS("Показать список операций для студента"),
    UNIVERSITY_ADMIN_OPERATIONS("Показать список операций для администратора");

    private final String universityMenuItem;

    UniversityMenuItems(String universityMenuItem) {
        this.universityMenuItem = universityMenuItem;
    }

    public String getUniversityMenuItem() {
        return universityMenuItem;
    }

    @Override
    public String toString() {
        return universityMenuItem;
    }
}
