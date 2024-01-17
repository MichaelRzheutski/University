package com.solvd.university.util.menus.enums;

public enum XmlConsoleSelectors {
    CONSOLE("CONSOLE"),
    STAX("STAX"),
    JAXB("JAXB"),
    JACKSON("JACKSON");

    private final String xmlConsoleSelector;

    XmlConsoleSelectors(String adminMenuItem) {
        this.xmlConsoleSelector = adminMenuItem;
    }

    public String getXmlConsoleSelector() {
        return xmlConsoleSelector;
    }

    @Override
    public String toString() {
        return xmlConsoleSelector;
    }
}
