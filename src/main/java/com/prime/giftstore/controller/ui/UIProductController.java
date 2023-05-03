package com.prime.giftstore.controller.ui;

import com.prime.giftstore.repo.model.Product;
import com.prime.giftstore.service.StoreService;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import static com.prime.giftstore.config.Constants.PRODUCTS_PAGE;
import static com.prime.giftstore.config.Constants.PRODUCT_FORM;
import static com.prime.giftstore.config.Constants.PRODUCT_MODEL;
import static com.prime.giftstore.config.Constants.PROFILE_CLIENT;
import static com.prime.giftstore.utils.IdUtils.generateId;

@Controller
@RequestMapping(value = "/products")
@Profile(value = PROFILE_CLIENT)
public class UIProductController extends UIBaseController<Product> {

    public UIProductController(StoreService<Product> service) {
        super(service);
    }

    @Override
    protected String getModelIdentifier() {
        return PRODUCT_MODEL;
    }

    @Override
    protected String getModelPage() {
        return PRODUCTS_PAGE;
    }

    @Override
    protected String getFormPage() {
        return PRODUCT_FORM;
    }

    @Override
    protected Product getNew() {
        return new Product().setId(generateId());
    }

    @Override
    protected void enhanceModel(Model model) {
        //NO-OP
    }
}
