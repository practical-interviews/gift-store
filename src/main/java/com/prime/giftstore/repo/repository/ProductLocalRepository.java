package com.prime.giftstore.repo.repository;

import com.prime.giftstore.repo.model.Product;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

@Component
public class ProductLocalRepository {
    private final ConcurrentHashMap<String, Product> productRepo = new ConcurrentHashMap<>();

    public Product save(Product product) {
        productRepo.put(product.getId(), product);
        return product;
    }
    
    public Optional<Product> findById(String id) {
        return Optional.ofNullable(productRepo.get(id));
    }

    public List<Product> searchByName(String name) {
        return productRepo.values().stream()
                .filter(n -> name.equalsIgnoreCase(n.getName()))
                .collect(Collectors.toList());
    }

    public List<Product> findAll() {
        return new ArrayList<>(productRepo.values());
    }

    public void deleteAll() {
        productRepo.clear();
    }
    
    public void deleteById(String id) {
        productRepo.remove(id);
    }
}
