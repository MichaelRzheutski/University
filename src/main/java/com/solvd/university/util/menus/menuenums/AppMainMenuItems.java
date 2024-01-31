package com.solvd.university.util.menus.menuenums;

public enum AppMainMenuItems {
    UNIVERSITY_JDBC("Работать с JDBC"),
    UNIVERSITY_MYBATIS("Работать с MyBatis");

    private final String appMainMenuItem;

    AppMainMenuItems(String appMainMenuItem) {
        this.appMainMenuItem = appMainMenuItem;
    }

    public String getAppMainMenuItem() {
        return appMainMenuItem;
    }

    @Override
    public String toString() {
        return appMainMenuItem;
    }

}
