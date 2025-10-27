package com.example.shoppinglist.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

@Entity
@NoArgsConstructor
@Getter
@Setter
public class Item {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String product;
    private int amount;
    private boolean purchase = false;


    // 여러 개의 아이템이 한 명의 유저에게 속하니까
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    @JsonIgnore
    private ShopUser user;


    // CommandLineRunner 상에서 사용할 간단한 생성자 정의
    public Item(Long id, String product, int amount, boolean purchase, ShopUser user) {
        this.id = id;
        this.product = product;
        this.amount = amount;
        this.purchase = purchase;
        this.user = user;
    }
}
