package com.example.shoppinglist.web;

import com.example.shoppinglist.dto.ShopUpdateRecord;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ShopController {
    @DeleteMapping("/api/shops/completed")
    public ShopUpdateRecord deleteShop(@RequestParam String content) {
        return new ShopUpdateRecord(content);
    }
}
