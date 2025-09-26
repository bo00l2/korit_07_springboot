package com.example.cardatabase;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import com.example.cardatabase.web.CarController;

@SpringBootTest
class CardatabaseApplicationTests {
	@Autowired
	private CarController controller; // final을 사용해서 생성자 주입도 가능

	@Test
	@DisplayName("First Example Test Case")
	void contextLoads() {
		Assertions.assertThat(controller).isNotNull();
	}

}
