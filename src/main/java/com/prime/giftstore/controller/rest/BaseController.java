package com.prime.giftstore.controller.rest;

import com.prime.giftstore.service.StoreService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import static java.util.Objects.isNull;

@CrossOrigin(maxAge = 3600)
public abstract class BaseController <T> {

    private final StoreService<T> service;

    protected BaseController(StoreService<T> service) {
        this.service = service;
    }

    @GetMapping(value = "")
    public ResponseEntity<Iterable<T>> getAll() {
        return ResponseEntity.ok(service.getAll());
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<T> get(@PathVariable String id) {
        T val = service.get(id);
        return isNull(val)
                ?  ResponseEntity.notFound().build()
                :  ResponseEntity.ok(service.get(id));
    }

    @PostMapping
    public ResponseEntity<T> create(@RequestBody T t) {
        return ResponseEntity.ok(service.create(t));
    }

    @PostMapping(value = "/generate")
    public ResponseEntity<Iterable<T>> generateData(@RequestParam(required = false, defaultValue = "3") Integer size) {
        return ResponseEntity.ok(service.generateData(size));
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<T> update(@PathVariable String id, @RequestBody T t) {
        return ResponseEntity.ok(service.update(t, id));
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Boolean> delete(@PathVariable String id) {
        T val = service.get(id);
        return isNull(val)
                ?  ResponseEntity.notFound().build()
                :  ResponseEntity.ok(service.delete(id));
    }

    @DeleteMapping(value = "/truncate")
    public ResponseEntity<Boolean> clearRegion() {
        return ResponseEntity.ok(service.clearRegion());
    }
}
