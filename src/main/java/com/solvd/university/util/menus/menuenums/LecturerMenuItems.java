package com.solvd.university.util.menus.menuenums;

public enum LecturerMenuItems {
    LECTURER_SHOW_GRADE_BOOK("Показать журнал успеваемости студентов");

    private final String lecturerMenuItem;

    LecturerMenuItems(String lecturerMenuItem) {
        this.lecturerMenuItem = lecturerMenuItem;
    }

    public String getLecturerMenuItem() {
        return lecturerMenuItem;
    }

    @Override
    public String toString() {
        return lecturerMenuItem;
    }
}
