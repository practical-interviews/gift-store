package com.prime.giftstore.service.impl;

import com.prime.giftstore.repo.model.Order;
import com.prime.giftstore.repo.repository.OrderLocalRepository;
import com.prime.giftstore.service.StoreService;
import org.apache.logging.log4j.util.Strings;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

import static com.prime.giftstore.config.Constants.PROFILE_CLIENT;
import static com.prime.giftstore.utils.IdUtils.generateId;

@Service
@Profile(value = PROFILE_CLIENT)
public class OrderService implements StoreService<Order> {

    private final OrderLocalRepository orderLocalRepository;

    public OrderService(OrderLocalRepository orderLocalRepository) {
        this.orderLocalRepository = orderLocalRepository;
    }

    @Override
    public Order create(Order order) {
        order.setId(generateId());
        return orderLocalRepository.save(order);
    }

    @Override
    public Order update(Order order, String id) {
        return Strings.isEmpty(order.getId()) ? create(order) : orderLocalRepository.save(order);
    }

    @Override
    public boolean delete(String id) {
        orderLocalRepository.delete(id);
        return true;
    }

    @Override
    public Order get(String id) {
        return orderLocalRepository.findById(id);
    }

    @Override
    public Iterable<Order> getAll() {
        return orderLocalRepository.findAll();
    }

    @Override
    public List<Order> search(String key) {
        return List.of(orderLocalRepository.findById(key));
    }

    @Override
    public boolean clearRegion() {
        orderLocalRepository.deleteAll();
        return true;
    }

    @Override
    public Iterable<Order> generateData(Integer size) {
        //no-op
        return Collections.emptyList();
    }
}
