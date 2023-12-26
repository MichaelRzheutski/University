package com.solvd.university.util.enums;

public enum BuildingItems {
    ADMIN_SHOW_ALL_BUILDINGS("Показать все корпуса"),
    ADMIN_ADD_BUILDING("Добавить корпус"),
    ADMIN_FIND_BUILDING_BY_ID("Найти корпус по ID"),
    ADMIN_UPDATE_BUILDING_NAME_BY_ID("Изменить имя корпуса по ID"),
    ADMIN_DELETE_BUILDING_BY_ID("Удалить корпус по ID"),
    ADMIN_COUNT_BUILDINGS("Посчитать общее количество корпусов");

    private final String BuildingItems;

    BuildingItems(String adminMenuItem) {
        this.BuildingItems = adminMenuItem;
    }

    public String getBuildingItems() {
        return BuildingItems;
    }

    @Override
    public String toString() {
        return BuildingItems;
    }
}
