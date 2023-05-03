package com.prime.giftstore.controller.ui;

import com.prime.giftstore.repo.model.Order;
import com.prime.giftstore.repo.model.Product;
import com.prime.giftstore.service.StoreService;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import static com.prime.giftstore.config.Constants.ORDERS_PAGE;
import static com.prime.giftstore.config.Constants.ORDER_FORM;
import static com.prime.giftstore.config.Constants.ORDER_MODEL;
import static com.prime.giftstore.config.Constants.PROFILE_CLIENT;
import static com.prime.giftstore.utils.IdUtils.generateId;

@Controller
@RequestMapping(value = "/orders")
@Profile(value = PROFILE_CLIENT)
public class UIOrderController extends UIBaseController<Order> {

  private final StoreService<Product> productService;
  public UIOrderController(StoreService<Order> service, StoreService<Product> productService) {
    super(service);
    this.productService = productService;
  }

  @Override
  protected String getModelIdentifier() {
    return ORDER_MODEL;
  }

  @Override
  protected String getModelPage() {
    return ORDERS_PAGE;
  }

  @Override
  protected String getFormPage() {
    return ORDER_FORM;
  }

  @Override
  protected Order getNew() {
    return new Order().setId(generateId());
  }

  @Override
  protected void enhanceModel(Model model) {
    Iterable<Product> products = productService.getAll();
    model.addAttribute("products", products);
  }

}
