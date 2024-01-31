package com.solvd.university.util.menus.enums;

public enum ParserSelectors {
    CONSOLE("CONSOLE"),
    STAX("STAX"),
    JAXB("JAXB"),
    JACKSON("JACKSON");

    private final String parserSelector;

    ParserSelectors(String adminMenuItem) {
        this.parserSelector = adminMenuItem;
    }

    public String getParserSelector() {
        return parserSelector;
    }

    @Override
    public String toString() {
        return parserSelector;
    }
}
