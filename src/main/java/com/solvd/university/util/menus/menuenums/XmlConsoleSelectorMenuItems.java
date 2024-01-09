package com.solvd.university.util.menus.menuenums;

public enum XmlConsoleSelectorMenuItems {
    DATA_PROVIDER_CONSOLE("Получать данные из консоли"),
    DATA_PROVIDER_XML("Получать данные из XML-файла"),
    DATA_PROVIDER_XML_JAXB("Получать данные из XML-файла используя JAXB");

    private final String XmlConsoleSelectorMenuItem;

    XmlConsoleSelectorMenuItems(String XmlConsoleSelectorMenuItem) {
        this.XmlConsoleSelectorMenuItem = XmlConsoleSelectorMenuItem;
    }

    public String getXmlConsoleSelectorMenuItem() {
        return XmlConsoleSelectorMenuItem;
    }

    @Override
    public String toString() {
        return XmlConsoleSelectorMenuItem;
    }
}