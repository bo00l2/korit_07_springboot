package com.example.shoppinglist;

import com.example.shoppinglist.domain.Item;
import com.example.shoppinglist.domain.ItemRepository;
import com.example.shoppinglist.domain.ShopUserRepository;
import org.slf4j.LoggerFactory;
import org.slf4j.Logger;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@SpringBootApplication
public class ShoppinglistApplication implements CommandLineRunner {
	private static final Logger logger = LoggerFactory.getLogger(
			ShoppinglistApplication.class
	);

	private final ShopUserRepository shopUserRepository;
	private final ItemRepository repository;

	public ShoppinglistApplication(ShopUserRepository shopUserRepository, ItemRepository repository) {
		this.shopUserRepository = shopUserRepository;
		this.repository = repository;
	}

	public static void main(String[] args) {
		SpringApplication.run(ShoppinglistApplication.class, args);
		logger.info("Application Started ! / 애플리케이션이 실행되었습니다.");
	}

	@Override
	public void run(String... args) throws Exception {

		repository.save(new Item("색종이", 3, 1500));
		repository.save(new Item("노트", 5, 2000));

		for(Item item : repository.findAll()){
			logger.info("product : {}, price : {}", item.getProduct(), item.getPrice());
		}
	}

	public static class ShopController {

	}
}
