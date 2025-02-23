package org.example.utils;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;
import org.example.entity.Rating;

@Converter(autoApply = true)
public class RatingConverter implements AttributeConverter<Rating, String> {
    @Override
    public String convertToDatabaseColumn(Rating rating) {
        return rating == null ? null : rating.name().replace("_", "-");
    }

    @Override
    public Rating convertToEntityAttribute(String dbData) {
        return dbData == null ? null : Rating.fromString(dbData);
    }
}
