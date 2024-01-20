package com.solvd.university.util.menus.menuenums;

public enum AdminMenuLecturerItems {
    ADMIN_SHOW_ALL_LECTURERS("Показать полную информацию о преподавателях"),
    ADMIN_ADD_LECTURER("Добавить преподавателя"),
    ADMIN_FIND_LECTURER_BY_ID("Найти преподавателя по ID"),
    ADMIN_UPDATE_LECTURER_INFO("Обновить информацию о преподавателе"),
    ADMIN_DELETE_LECTURER("Удалить преподавателя"),
    ADMIN_COUNT_LECTURERS("Посчитать общее количество преподавателей");

    private final String adminMenuLecturerItem;

    AdminMenuLecturerItems(String adminMenuLecturerItem) {
        this.adminMenuLecturerItem = adminMenuLecturerItem;
    }

    public String getAdminMenuLecturerItem() {
        return adminMenuLecturerItem;
    }

    @Override
    public String toString() {
        return adminMenuLecturerItem;
    }
}
