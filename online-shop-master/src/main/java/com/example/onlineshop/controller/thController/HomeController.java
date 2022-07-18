package com.example.onlineshop.controller.thController;

import com.example.onlineshop.dto.SaveClient;
import com.example.onlineshop.entity.Category;
import com.example.onlineshop.exceptionHandler.CategoryNotFoundException;
import com.example.onlineshop.exceptionHandler.ProductNotFoundException;
import com.example.onlineshop.service.*;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
public class HomeController {

    private final ProductService productService;
    private final BannerService bannerService;
    private final BlogService blogService;
    private final CategoryService categoryService;
    private final OrderService orderService;
    private final ClientService clientService;

    public HomeController(ProductService productService, BannerService bannerService, BlogService blogService, CategoryService categoryService, OrderService orderService, ClientService clientService) {
        this.productService = productService;
        this.bannerService = bannerService;
        this.blogService = blogService;
        this.categoryService = categoryService;
        this.orderService = orderService;
        this.clientService = clientService;
    }

    @GetMapping("/")
    public String showHello(final ModelMap modelMap) {
        var products = productService.productList();
        modelMap.addAttribute("products", products);

        var banners = bannerService.bannerList();
        modelMap.addAttribute("banners", banners);

        var banner = banners.size() > 0 ? banners.get(0) : null;
        modelMap.addAttribute("banner", banner);

        var categories = categoryService.categoryList();
        modelMap.addAttribute("categories", categories);
        for (Category category : categories) {
            modelMap.addAttribute(category.getName(), productService.productsByCategory(category.getId()));
        }

        var posts = blogService.blogList();
        modelMap.addAttribute("posts", posts);


        return "index8";
    }

    @GetMapping("/categories/{id}")
    public String showCategory(Model model, @PathVariable int id) throws CategoryNotFoundException {
        model.addAttribute("products", productService.productsByCategory(id));
        model.addAttribute("category", categoryService.findCategoryById(id));
        return "products-by-category";
    }

    @GetMapping("/register")
    public String initCreationForm(Model model) {
        model.addAttribute("client", SaveClient.builder().build());
        return "createOrUpdateClientForm";
    }

    @PostMapping("/register")
    public String processCreationForm(SaveClient request, BindingResult result) {
        if (result.hasErrors()) {
            return "createOrUpdateClientForm";
        } else {
            int id =  clientService.save(request);
            return "redirect:/";
        }
    }

    @GetMapping("/products/{id}")
    private String findProductById(@PathVariable int id, Model model) throws ProductNotFoundException {
        model.addAttribute("product", productService.findById(id));
        return "products-details";  //TODO
    }
}
