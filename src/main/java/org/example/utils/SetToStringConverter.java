package org.example.utils;

import jakarta.persistence.AttributeConverter;

import java.util.HashSet;

import java.util.Set;

public class SetToStringConverter implements AttributeConverter<Set<String>, String> {

    @Override
    public String convertToDatabaseColumn(Set<String> strings) {
        if (strings == null || strings.isEmpty()) {
            return "";
        }
        return String.join(",", strings);
    }

    @Override
    public Set<String> convertToEntityAttribute(String s) {
        if (s == null || s.isEmpty()) {
            return new HashSet<>();
        }
        return Set.of(s.split(","));
    }
}
