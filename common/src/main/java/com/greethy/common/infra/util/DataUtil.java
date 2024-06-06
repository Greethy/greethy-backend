package com.greethy.common.infra.util;

import com.greethy.common.domain.exception.DataException;
import lombok.extern.slf4j.Slf4j;

import java.text.DecimalFormat;
import java.text.Format;
import java.util.Collection;

@Slf4j
public class DataUtil {

    private static final Format decimalFormat = new DecimalFormat("#.##");

    public static boolean isNullOrEmpty(Object object) {
        return object == null;
    }

    public static boolean isNullOrEmpty(final Collection<?> collection) {
        return collection == null || collection.isEmpty();
    }

    public static double toDoubleSafely(Object object) {
        return toDoubleSafely(object, 0.0d);
    }

    public static double toDoubleSafely(Object object, Double defaultValue) {
        if (isNullOrEmpty(object)) return defaultValue;
        try {
            return Double.parseDouble(decimalFormat.format(object));
        } catch (Exception ex) {
            log.error("To Double safe throw some error: {}", ex.getMessage());
            throw new DataException(ex.getMessage());
        }
    }

}
