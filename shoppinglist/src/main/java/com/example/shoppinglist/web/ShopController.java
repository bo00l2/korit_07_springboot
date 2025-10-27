package com.example.shoppinglist.web;

import com.example.shoppinglist.domain.Item;
import com.example.shoppinglist.domain.ItemRepository;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ShopController {
    private final ItemRepository itemRepository;

    public ShopController(ItemRepository itemRepository) {
        this.itemRepository = itemRepository;
    }

    @GetMapping("/items")
    public Iterable<Item> getUser(){
        return itemRepository.findAll();
    }

//    @DeleteMapping("/api/items/completed")
//    public ShopUpdateRecord deleteShop(@RequestParam String content) {
//        return new ShopUpdateRecord(content);
//    }
}
