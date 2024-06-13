package com.greethy.common.infra.util;

import java.util.List;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.Collectors;

public class RandomUtil {

    public static List<String> getListRandomFromStrings(int size, List<String> strings) {
        var length = Math.max(0, Math.min(size, strings.size()));
        return ThreadLocalRandom.current()
                .ints(0, strings.size())
                .distinct()
                .limit(length)
                .mapToObj(strings::get)
                .collect(Collectors.toList());
    }

    public static String getSingleRandomFromStrings(String... strings) {
        return getSingleRandomFromStrings(List.of(strings));
    }

    public static String getSingleRandomFromStrings(List<String> strings) {
        return ThreadLocalRandom.current()
                .ints(0, strings.size())
                .mapToObj(strings::get)
                .findFirst().orElse("");
    }

    public static Integer getSingleRandomInteger(int from, int to) {
        return ThreadLocalRandom.current()
                .ints(from, to)
                .findFirst()
                .orElse(0);
    }

}
