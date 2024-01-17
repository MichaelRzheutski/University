package com.solvd.university.util.menus.enums;

public enum ControllerTypes {
    JDBC("JDBC"),
    MYBATIS("MYBATIS");

    private final String controllerType;

    ControllerTypes(String adminMenuItem) {
        this.controllerType = adminMenuItem;
    }

    public String getControllerType() {
        return controllerType;
    }

    @Override
    public String toString() {
        return controllerType;
    }
}
