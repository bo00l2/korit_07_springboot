package com.shoppinglist2.shoppinglist2.domain;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.List;
import java.util.Optional;

//@RepositoryRestResource(exported = false)
public interface ShopItemRepository extends JpaRepository<ShopItem, Long> {
    // 사용자 정의 컨트롤러에서 사용
    List<ShopItem> findByUser(User user);

    // 삭제 / 수정 시 authentication 확인용
    Optional<ShopItem> findByIdAndUser(Long id, User user);
}
