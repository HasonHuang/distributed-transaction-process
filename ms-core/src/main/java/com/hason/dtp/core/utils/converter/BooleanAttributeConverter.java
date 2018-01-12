package com.hason.dtp.core.utils.converter;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

/**
 * 实现 Java Boolean <-> 数据库 int
 *
 * @author Huanghs
 * @since 2.0
 * @date 2017/10/19
 */
@Converter(autoApply = true)
public class BooleanAttributeConverter implements AttributeConverter<Boolean, Integer> {
    @Override
    public Integer convertToDatabaseColumn(Boolean attribute) {
        return Boolean.TRUE.equals(attribute) ? 1 : 0;
    }

    @Override
    public Boolean convertToEntityAttribute(Integer dbData) {
        return dbData != null && dbData > 0 ? Boolean.TRUE : Boolean.FALSE;
    }
}
