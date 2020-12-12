package com.igpodg.eyentelligence.util;

import java.util.Optional;

public class OptionalUtil {
    public static <T> T unwrap(Optional<T> value) {
        return (value == null) ? null : value.orElse(null);
    }

    public static <T> boolean isValid(Optional<T> value) {
        return (value != null && value.isPresent());
    }
}
