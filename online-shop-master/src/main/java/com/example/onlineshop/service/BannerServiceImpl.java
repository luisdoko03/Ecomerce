package com.example.onlineshop.service;
import com.example.onlineshop.dto.SaveBannerRequest;
import com.example.onlineshop.entity.Banner;
import com.example.onlineshop.entity.Category;
import com.example.onlineshop.exceptionHandler.BannerNotFoundException;
import com.example.onlineshop.exceptionHandler.CategoryNotFoundException;
import com.example.onlineshop.repository.BannerRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class BannerServiceImpl implements BannerService {
    private final BannerRepository bannerRepository;

    public BannerServiceImpl(BannerRepository bannerRepository) {
        this.bannerRepository = bannerRepository;
    }


    public int save(SaveBannerRequest request) {
        var dbBanner = bannerRepository.findById(request.getId());
        if (dbBanner.isPresent()) {
            dbBanner.get().setName(request.getName());
            dbBanner.get().setPhotoUrl(request.getPhotoUrl());
            dbBanner.get().setModified(LocalDateTime.now());
            bannerRepository.save(dbBanner.get());
            return dbBanner.get().getId();
        } else {

            var newBanner = new Banner();
            newBanner.setName(request.getName());
            newBanner.setPhotoUrl(request.getPhotoUrl());
            newBanner.setCreated(LocalDateTime.now());
            bannerRepository.save(newBanner);
            return newBanner.getId();
        }
    }
    public List<Banner> bannerList() {
        return bannerRepository.findAll();
    }

    public Banner findBannerById(int id) throws BannerNotFoundException {
        Optional<Banner> bannerOptional = bannerRepository.findById(id);
        if(bannerOptional.isPresent()){
            return bannerOptional.get();
        }else{
            throw new BannerNotFoundException();}
    }
    public void deleteById(int id) {
        bannerRepository.deleteById(id);
    }
}