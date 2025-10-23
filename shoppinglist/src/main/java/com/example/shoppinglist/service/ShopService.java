package com.example.shoppinglist.service;

import com.example.shoppinglist.domain.ShopUser;
import com.example.shoppinglist.domain.ShopUserRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class ShopService {
    private final ShopUserRepository shopRepository;

    public ShopService(ShopUserRepository shopRepository) {
        this.shopRepository = shopRepository;
    }

    public List<ShopUser> getShop() {return shopRepository.findAll();}

    public Optional<ShopUser> getShopById(long id) {return shopRepository.findById(id);}

    public ShopUser addShop(ShopUser shopUser) {return shopRepository.save(shopUser);}

    public boolean deleteShop(Long id){
        if(shopRepository.existsById(id)){
            shopRepository.deleteById(id);
            return true;
        }
        return false;
    }

    @Transactional
    public Optional<ShopUser> updateShopStatus(Long id, ShopUser userDetails){
        return shopRepository.findById(id)
                .map(shop -> {
                    shop.setId(userDetails.getId());
                    shop.set(userDetails.getUser());
                    shop.setContent(userDetails.getContent());
                    return shop;
                });
    }
}
