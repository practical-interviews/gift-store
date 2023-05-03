package com.prime.giftstore.controller.ui;

import com.prime.giftstore.service.StoreService;
import org.springframework.data.repository.query.Param;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.ArrayList;
import java.util.List;

import static com.prime.giftstore.config.Constants.MESSAGE;
import static com.prime.giftstore.config.Constants.PAGE_TITLE;

@CrossOrigin(maxAge = 3600)
public abstract class UIBaseController<T> {
    private final StoreService<T> service;
    protected UIBaseController(StoreService<T> service) {
        this.service = service;
    }

    protected abstract String getModelIdentifier();
    protected abstract String getModelPage();
    protected abstract String getFormPage();
    protected abstract T getNew();
    protected abstract void enhanceModel(Model model);

    @GetMapping
    public String getAll(Model model, @Param("keyword") String keyword) {
        try {
            List<T> lists = new ArrayList<>();
            if (keyword == null) {
                service.getAll().forEach(lists::add);
            } else {
                lists.addAll(service.search(keyword));
                model.addAttribute("keyword", keyword);
            }
            model.addAttribute(getModelPage(), lists);
        } catch (Exception e) {
            model.addAttribute(MESSAGE, e.getMessage());
        }

        return getModelPage();
    }

    @GetMapping("/new")
    public String addRecord(Model model) {
        model.addAttribute(getModelIdentifier(), getNew());
        enhanceModel(model);
        model.addAttribute(PAGE_TITLE, "CREATE "+getModelIdentifier().toUpperCase());

        return getFormPage();
    }

    @PostMapping("/save")
    public String saveRecord(T t, @RequestParam(required = false) String id, RedirectAttributes redirectAttributes) {
        try {
            service.update(t, id);
            redirectAttributes.addFlashAttribute(MESSAGE, getModelIdentifier().toUpperCase()+" HAS BEEN SAVED SUCCESSFULLY!");
        } catch (Exception e) {
            redirectAttributes.addAttribute(MESSAGE, e.getMessage());
        }
        return "redirect:/"+getModelPage();
    }

    @GetMapping("/{id}")
    public String editRecord(@PathVariable("id") String id, Model model, RedirectAttributes redirectAttributes) {
        try {
            T record = service.get(id);
            model.addAttribute(getModelIdentifier(), record);
            enhanceModel(model);
            model.addAttribute(PAGE_TITLE, "EDIT " + getModelIdentifier().toUpperCase()+ " (ID: " + id + ")");
            return getFormPage();
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute(MESSAGE, e.getMessage());

            return "redirect:/"+getModelPage();
        }
    }

    @GetMapping("/delete/{id}")
    public String deleteRecord(@PathVariable("id") String id, Model model, RedirectAttributes redirectAttributes) {
        try {
            service.delete(id);
            redirectAttributes.addFlashAttribute(MESSAGE, getModelIdentifier().toUpperCase()+ " WITH ID=" + id + " HAS BEEN DELETED SUCCESSFULLY!");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute(MESSAGE, e.getMessage());
        }
        return "redirect:/"+getModelPage();
    }

    @GetMapping(value = "/truncate")
    public String clearRegion(RedirectAttributes redirectAttributes) {
        try {
            service.clearRegion();
            redirectAttributes.addFlashAttribute(MESSAGE, "ALL "+getModelIdentifier().toUpperCase()+ " HAVE BEEN DELETED SUCCESSFULLY!");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute(MESSAGE, e.getMessage());
        }
        return "redirect:/"+getModelPage();
    }
}
