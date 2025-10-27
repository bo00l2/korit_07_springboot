package com.example.shoppinglist.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@NoArgsConstructor(force = true)
@Data
@Table(name = "users") //
public class ShopUser {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(nullable = false, updatable = false)
    private Long id;

    @Column(nullable = false, unique = true)
    private final String username;

    @Column(nullable = false)
    @JsonIgnore // 풀이: 비밀번호가 API 응답에 노출되지 않도록 했다. 어차피 암호화할거라서.
    private final String password;

    @Column(nullable = false)
    private final String role;

    // 한 명의 유저는 여러 개의 쇼핑 아이템을 가진다.
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "user", orphanRemoval = true) // orphanRemoval = true
    @JsonIgnore // 순환참조를 일으키지 않도록 함
    private List<Item> items;

    // CommandLineRunner 상에서 사용할 간단한 생성자 정의
    public ShopUser(String username, String password, String role){
        this.username = username;
        this.password = password;
        this.role = role;
    }
}
