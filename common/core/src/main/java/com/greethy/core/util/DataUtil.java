package com.greethy.core.util;

import lombok.extern.slf4j.Slf4j;

import java.util.Collection;

@Slf4j
public class DataUtil {

    public static boolean isNullOrEmpty(Object object) {
        return object == null;
    }

    public static boolean isNullOrEmpty(final Collection<?> collection) {
        return collection == null || collection.isEmpty();
    }

    public static boolean isNullOrEmpty(final Object[] collection) {
        return collection == null || collection.length == 0;
    }

}
