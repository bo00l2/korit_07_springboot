package com.example.cardatabase;

import com.example.cardatabase.domain.AppUserRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
public class AppRepositoryTest {
    @Autowired
    private AppUserRepository appUserRepository;

    @Test
    @DisplayName("사용자 조회 테스트")
    void ReturnAppUser(){
        // given
        appUserRepository.save(new AppUser("user1", "1234", "USER"));

        // when
        Optional<AppUser> users = appUserRepository.findByUsername("user1");

        // then
        assertThat(users).isPresent();
        assertThat(users.get().getRole()).isEqualTo("USER");
        // user 자체가 AppUser 자체가 아니기 때문에 객체 그 자체를 꺼내기 위해서 get()을 사용함.
    }
}
