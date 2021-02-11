package com.igpodg.eyentelligence.util;

import java.util.Optional;

public class OptionalUtil {
    public static <T> T unwrap(Optional<T> value) {
        if (value == null)
            System.out.println("optional is null");
        else if (value.isEmpty())
            System.out.println("optional contents are null");
        return (value == null) ? null : value.orElse(null);
    }

    public static <T> boolean isValid(Optional<T> value) {
        return (value != null && value.isPresent());
    }
}
