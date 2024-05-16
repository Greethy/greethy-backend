package com.greethy.nutrition.infra.util;

import java.util.Collection;
import java.util.Random;

import org.springframework.stereotype.Component;

/**
 * The {@code RandomUtils} class provides utility methods for working with randomization.
 *
 * @author KienThanh
 */
@Component
public class RandomUtils {

    /**
     * Returns a random element from the specified collection.
     *
     * <p>
     * This method takes a collection and returns a random element from it. It uses the
     * Stream API to skip a random number of elements from the beginning of the collection,
     * and then returns the first element encountered after skipping. If the collection
     * is empty, it throws an {@code IllegalArgumentException}.
     * </p>
     *
     * @param <T>        the type of elements in the collection
     * @param collection the collection from which to select a random element
     * @return a random element from the collection
     * @throws IllegalArgumentException if the collection is empty
     */
    public static <T> T randomizeElement(Collection<T> collection) throws IllegalArgumentException {
        return collection.stream()
                .skip(new Random().nextInt(collection.size()))
                .findFirst()
                .orElseThrow(IllegalArgumentException::new);
    }
}
