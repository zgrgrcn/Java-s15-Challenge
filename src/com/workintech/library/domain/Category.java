package com.workintech.library.domain;

public enum Category {
    FICTION, NON_FICTION, HISTORY, SCIENCE, TECHNOLOGY, MAGAZINE;

    public static Category safeValueOf(String value) {
        try {
            return Category.valueOf(value.trim().toUpperCase());
        } catch (Exception e) {
            return FICTION;
        }
    }
}
