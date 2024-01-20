package com.solvd.university.util.menus.menuenums;

public enum AdminMenuStudentItems {
    ADMIN_SHOW_ALL_STUDENTS("Показать полную информацию о студентах"),
    ADMIN_ADD_STUDENT("Добавить студента"),
    ADMIN_FIND_STUDENT_BY_ID("Найти студента по ID"),
    ADMIN_UPDATE_STUDENT_INFO("Обновить информацию о студенте"),
    ADMIN_DELETE_STUDENT("Удалить студента"),
    ADMIN_COUNT_STUDENTS("Посчитать общее количество студентов");

    private final String adminMenuStudentItem;

    AdminMenuStudentItems(String adminMenuStudentItem) {
        this.adminMenuStudentItem = adminMenuStudentItem;
    }

    public String getAdminMenuStudentItem() {
        return adminMenuStudentItem;
    }

    @Override
    public String toString() {
        return adminMenuStudentItem;
    }
}
