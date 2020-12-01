package com.smart.california.domain;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

/**
 * Hibernate Converter for the Region Enum to DB Column.
 *
 * Created by Mary Ellen Bowman
 */
@Converter(autoApply = true)
public class RegionConverter implements AttributeConverter<com.smart.california.domain.Region, String>{
    @Override
    public String convertToDatabaseColumn(com.smart.california.domain.Region region) {
        return region.getLabel();
    }

    @Override
    public com.smart.california.domain.Region convertToEntityAttribute(String s) {
        return com.smart.california.domain.Region.findByLabel(s);
    }
}
