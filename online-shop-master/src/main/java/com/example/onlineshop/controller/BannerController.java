package com.example.onlineshop.controller;

import com.example.onlineshop.dto.SaveBannerRequest;
import com.example.onlineshop.entity.Banner;
import com.example.onlineshop.exceptionHandler.BannerNotFoundException;
import com.example.onlineshop.service.BannerService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("/api/banner")
public class BannerController {
    private final BannerService bannerService;

    public BannerController(BannerService bannerService) {
        this.bannerService = bannerService;
    }

    @PostMapping
    public int save(@RequestBody SaveBannerRequest request) {
        return bannerService.save(request);
    }

    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable int id) {
        bannerService.deleteById(id);
    }

    @GetMapping("/all")
    public List<Banner> findAll() {
        return bannerService.bannerList();
    }

    @GetMapping("/{id}")
    public Banner findById(@PathVariable("id") int id) {

        try {
            return bannerService.findBannerById(id);

        } catch (BannerNotFoundException c) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Banner Not Found", c);
        }

    }

}
