package com.example.shoppinglist.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Entity
@RequiredArgsConstructor
@Getter
@Setter
public class Item {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String product;
    private int amount, price;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user")
    private ShopUser user;

    public ShopUser getUser() {
        return user;
    }

    public void setUser(ShopUser user) {
        this.user = user;
    }

    public Item(String product, int amount, int price, ShopUser user){
        this.product = product;
        this.amount = amount;
        this.price = price;
        this.user = user;
    }
}
