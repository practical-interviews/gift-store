package com.prime.giftstore.service;

import java.util.List;

public interface StoreService<T> {
    T create(T t);
    T update(T t, String id);
    boolean delete(String id);
    T get(String id);
    Iterable<T> getAll();
    List<T> search(String key);
    boolean clearRegion();
    Iterable<T> generateData(Integer size);
}
