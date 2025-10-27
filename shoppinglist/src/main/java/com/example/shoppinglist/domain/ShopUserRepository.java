package com.example.shoppinglist.domain;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.Optional;

// Spring Data Rest에서 노출하지 않도록 함
@RepositoryRestResource(exported = false)
public interface ShopUserRepository extends JpaRepository<ShopUser, Long> {

    // UserDetailsService에서 사용할 수 있도록 추상 메서드 하나 정의
    Optional<ShopUser> findByUsername(String username);
}
