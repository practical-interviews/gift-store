package com.prime.giftstore.utils;

import com.prime.giftstore.repo.model.Product;

import java.util.List;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static com.prime.giftstore.utils.IdUtils.generateId;

public final class DataUtil {

    public static List<Product> generateProducts(int size) {
        return IntStream.range(0, size)
                .mapToObj(id -> generateId())
                .map(dt -> new Product()
                        .setId(dt)
                        .setName("prod-"+ dt)
                        .setDescription("dsc-"+ dt)
                        .setQuantity(ThreadLocalRandom.current().nextInt(1, 10))
                        .setPrice(ThreadLocalRandom.current().nextDouble(1, 10)))
                .collect(Collectors.toList());
    }
}
