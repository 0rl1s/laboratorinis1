package com.example.laboratorinis1.services;

public class MokyklaNameGenerator implements NameGenerator {
    public String generateName(String name) {
        String generatedName = name + "mokykla";
        return generatedName;
    }
}
