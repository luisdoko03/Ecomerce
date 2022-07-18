package com.example.onlineshop.service;

import com.example.onlineshop.dto.SaveBannerRequest;
import com.example.onlineshop.entity.Banner;
import com.example.onlineshop.exceptionHandler.BannerNotFoundException;

import java.util.List;

public interface BannerService {
    int save(SaveBannerRequest request);
    List<Banner> bannerList();
    Banner findBannerById(int id) throws BannerNotFoundException;
    void deleteById(int id);
}
