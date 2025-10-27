package com.shoppinglist2.shoppinglist2.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import com.shoppinglist2.shoppinglist2.domain.User;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ShopItem {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String product;
    private String amount;
    private boolean purchased = false;

    // 여러 개의 아이템이 한 명의 유저에게 속하니까
    @ManyToOne
    @JoinColumn(name="user_id") // Item 테이블에 FK 컬럼을 만들어서 User 테이블의 id를 참조하게함.
    @JsonIgnore
    private com.shoppinglist2.shoppinglist2.domain.User user;

    // CommandLineRunner 상에서 사용할 간단한 생성자 정의
    public ShopItem(String product, String amount, boolean purchased, User user) {
        this.product = product;
        this.amount = amount;
        this.purchased = purchased;
        this.user = user;
    }
}