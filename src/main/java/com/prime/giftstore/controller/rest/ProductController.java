package com.prime.giftstore.controller.rest;

import com.prime.giftstore.repo.model.Product;
import com.prime.giftstore.service.StoreService;
import org.springframework.context.annotation.Profile;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static com.prime.giftstore.config.Constants.PROFILE_CLIENT;

@RestController
@RequestMapping(value = "/rest/products", produces = MediaType.APPLICATION_JSON_VALUE)
@Profile(value = PROFILE_CLIENT)
public class ProductController extends BaseController<Product> {

    protected ProductController(StoreService<Product> service) {
        super(service);
    }
}
