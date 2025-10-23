package com.example.shoppinglist.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity
@NoArgsConstructor(force = true)
@Getter
@Setter
@RequiredArgsConstructor
public class ShopUser {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false, updatable = false)
    private Long id;

    @Column(nullable = false, unique = true)
    private final String username;

    @Column(nullable = false)
    private final String password;

    @Column(nullable = false)
    private final String role;

//    @JsonIgnore
//    @OneToMany(cascade = CascadeType.ALL, mappedBy = "user")
//    private List<Item> shops;
}
