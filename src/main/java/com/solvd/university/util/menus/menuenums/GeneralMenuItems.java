package com.solvd.university.util.menus.menuenums;

public enum GeneralMenuItems {
    UNIVERSITY_PREVIOUS_MENU("Выйти в предыдущее меню"),
    UNIVERSITY_EXIT("Выйти из программы");

    private final String generalMenuItem;

    GeneralMenuItems(String generalMenuItem) {
        this.generalMenuItem = generalMenuItem;
    }

    public String getGeneralMenuItem() {
        return generalMenuItem;
    }

    @Override
    public String toString() {
        return generalMenuItem;
    }
}
