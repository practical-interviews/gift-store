package com.prime.giftstore.repo.repository;

import com.prime.giftstore.repo.model.Order;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

@Component
public class OrderLocalRepository {
    private final ConcurrentHashMap<String, Order> orderRepo = new ConcurrentHashMap<>();
    private final ProductLocalRepository productRepository;

    public OrderLocalRepository(ProductLocalRepository repository) {
        this.productRepository = repository;
    }

    public Order save(Order order) {
        orderRepo.put(order.getId(), order);
        return order;
    }
    
    public Order findById(String id) {
        return orderRepo.get(id);
    }

    public List<Order> findAll() {
        return new ArrayList<>(orderRepo.values());
    }

    public void deleteAll() {
        orderRepo.clear();
    }
    
    public void delete(String id) {
        orderRepo.remove(id);
    }
}
