package com.workintech.library.domain;

public class Author {
    private final String name;

    public Author(String name) {
        this.name = name.trim();
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return name;
    }
}
