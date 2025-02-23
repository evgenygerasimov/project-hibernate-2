package org.example.utils;

import jakarta.persistence.AttributeConverter;

import java.time.Year;

public class YearAttributeConverter implements AttributeConverter<Year, Integer> {
    @Override
    public Integer convertToDatabaseColumn(Year year) {
        return year == null? null : year.getValue();
    }

    @Override
    public Year convertToEntityAttribute(Integer integer) {
        return integer == null? null : Year.of(integer);
    }
}
