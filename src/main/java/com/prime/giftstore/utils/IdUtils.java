package com.prime.giftstore.utils;

import java.util.UUID;

public final class IdUtils {

    public static String generateId() {
        String id = UUID.randomUUID().toString();
        return id.substring(0, id.indexOf("-"));
    }
}
