package com.prime.giftstore.service.impl;

import com.prime.giftstore.repo.model.Product;
import com.prime.giftstore.repo.repository.ProductLocalRepository;
import com.prime.giftstore.service.StoreService;
import org.apache.geode.cache.GemFireCache;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static com.prime.giftstore.config.Constants.PROFILE_CLIENT;
import static com.prime.giftstore.utils.DataUtil.generateProducts;
import static com.prime.giftstore.utils.IdUtils.generateId;
import static java.util.Objects.isNull;

@Service
@Profile(value = PROFILE_CLIENT)
public class ProductService implements StoreService<Product> {

    private final ProductLocalRepository repository;
    private final GemFireCache cache;

    public ProductService(ProductLocalRepository repository,
                          GemFireCache cache) {
        this.repository = repository;
        this.cache = cache;
    }

    @Override
    public Product create(Product product) {
        if(isNull(product.getId())) product.setId(generateId());
        return repository.save(product);
    }

    @Override
    public Product update(Product product, String id) {
        return repository.save(product);
    }

    @Override
    public boolean delete(String id) {
        repository.deleteById(id);
        return true;
    }

    @Override
    public Product get(String id) {
        return repository.findById(id).orElse(null);
    }

    @Override
    public Iterable<Product> getAll() {
        return repository.findAll();
    }

    @Override
    public List<Product> search(String key) {
        return repository.searchByName(key);
    }

    @Override
    public boolean clearRegion() {
        repository.deleteAll();
        return Optional.ofNullable(getAll()).isEmpty();
    }

    @Override
    public Iterable<Product> generateData(Integer size) {
        return generateProducts(size)
                .stream().map(repository::save)
                .collect(Collectors.toList());
    }
}
