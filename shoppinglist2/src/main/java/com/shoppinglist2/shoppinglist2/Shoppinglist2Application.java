package com.shoppinglist2.shoppinglist2;

import com.shoppinglist2.shoppinglist2.domain.ShopItem;
import com.shoppinglist2.shoppinglist2.domain.ShopItemRepository;
import com.shoppinglist2.shoppinglist2.domain.User;
import com.shoppinglist2.shoppinglist2.domain.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.password.PasswordEncoder;

@SpringBootApplication
@RequiredArgsConstructor
public class Shoppinglist2Application implements CommandLineRunner {

	private final UserRepository userRepository;
	private final ShopItemRepository shopItemRepository;
	private final PasswordEncoder passwordEncoder;

	public static void main(String[] args) {
		SpringApplication.run(Shoppinglist2Application.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		// 테스트 사용자 생성
		User user = new User("user", passwordEncoder.encode("user"), "USER");
		userRepository.save(user);

		User admin = new User("admin", passwordEncoder.encode("admin"), "ADMIN");
		userRepository.save(admin);

		// user 쇼핑 아이템 더미 데이터 추가
		shopItemRepository.save(new ShopItem("우유", "1개", false, user));
		shopItemRepository.save(new ShopItem("빵", "1봉지", false, user));

		// admin 쇼핑 아이템 더미 데이터 추가
		shopItemRepository.save(new ShopItem("사과", "3개", false, admin));
	}
}
