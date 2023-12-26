package com.solvd.university.util.menus.menuenums;

public enum AdminMenuItems {
    ADMIN_SHOW_ALL_STUDENTS("Показать всех студентов"),
    ADMIN_ADD_STUDENT_CONTACT("Добавить контакт студента"),
    ADMIN_ADD_STUDENT("Добавить студента"),
    ADMIN_FIND_STUDENT_BY_ID("Найти студента по ID"),
    ADMIN_UPDATE_STUDENT_INFO("Обновить информацию о студенте"),
    ADMIN_DELETE_STUDENT("Удалить студента"),
    ADMIN_COUNT_STUDENTS("Посчитать общее количество студентов");

    private final String adminMenuItem;

    AdminMenuItems(String adminMenuItem) {
        this.adminMenuItem = adminMenuItem;
    }

    public String getAdminMenuItem() {
        return adminMenuItem;
    }

    @Override
    public String toString() {
        return adminMenuItem;
    }
}
