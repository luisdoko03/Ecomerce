package com.example.onlineshop.controller.thController;

import com.example.onlineshop.dto.SaveOrderClient;
import com.example.onlineshop.dto.SaveOrderRequest;
import com.example.onlineshop.exceptionHandler.ProductNotFoundException;
import com.example.onlineshop.service.OrderService;
import com.example.onlineshop.service.ProductService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@Controller
public class OrderThController {

    private final OrderService orderService;
    private final ProductService productService;

    public OrderThController(OrderService orderService, ProductService productService) {
        this.orderService = orderService;
        this.productService = productService;
    }

//    @GetMapping("/orders")
//    public String myOrders(Model model, int id) {
//        model.addAttribute("order", orderService.findOrdersByClientId(id));
//        return "";
//    }

    @PostMapping("/orders/new")
    public String createOrder(@RequestBody SaveOrderRequest request) {
        orderService.save(request);
        return "redirect:/orders";
    }

    @GetMapping("products/{id}/new-order")
    public String initCreationFormOrder(@PathVariable int id, Model model) throws ProductNotFoundException {
        var product = productService.findById(id);
        model.addAttribute("product", product);

        var request = SaveOrderClient.builder()
                .productId(id)
                .finalPrice(product.getPrice())
                .build();
        model.addAttribute("order", request);

        return "products-details";
    }

    @PostMapping("/products/{id}/new-order")
    public String processCreationFormOrder(@PathVariable int id, SaveOrderClient request, BindingResult result) {
        //request.setProductId(id);
        if (result.hasErrors()) {
            return "products-details";
        } else {
            orderService.saveOrderAndClient(request);
            return "redirect:/";
        }
    }
}
