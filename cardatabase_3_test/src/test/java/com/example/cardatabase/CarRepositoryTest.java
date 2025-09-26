package com.example.cardatabase;

import com.example.cardatabase.domain.Car;
import com.example.cardatabase.domain.CarRepository;
import com.example.cardatabase.domain.Owner;
import com.example.cardatabase.domain.OwnerRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
public class CarRepositoryTest {
    @Autowired
    private CarRepository carRepository;
    @Autowired
    private OwnerRepository ownerRepository;

    @Test
    @DisplayName("차량 저장 메서드 테스트")
    void saveCar() {
        // given - 제반 준비 사항
        // Car Entity를 확인해봤을 때 field로 Owner를 요구하기 때문에
        // 얘부터 먼저 만들고 -> OwnerRepository에 저장
        Owner owner = new Owner("Gemini", "GPT");
        ownerRepository.save(owner);
        // 그리고 Car 객체 생성
        Car car = new Car("Ford", "Mustang", "Red", "ABCDEF", 2021, 567890, owner);

        // when - 테스트 실행
        // 저장이 됐는가를 확인하기 위한 부분
        carRepository.save(car);

        // then - 그 결과가 어떠할지
        assertThat(carRepository.findById(car.getId())).isPresent();    // 이건 그냥 결과값이 하나일테니까.

        assertThat(carRepository.findById(car.getId()).get().getBrand()).isEqualTo("Ford");
    }

    @Test
    @DisplayName("차량 삭제 테스트")
    void deleteCars(){
        // given -> Owner 객체 생성 / 저장 -> Car 객체 생성 / 저장
        Owner owner = new Owner("동차", "차");
        ownerRepository.save(owner);
        Car car = new Car("Ford", "Mustang", "blue", "ABCDEF", 2021, 567890, owner);
        carRepository.save(car);
        // when -> 삭제
        carRepository.deleteAll();
//        carRepository.deleteById(car.getId());

        // then -> 삭제가 올바르게 되었는지 검증하는 assertThat() 구문
        assertThat(carRepository.count()).isEqualTo(0);
    }

    @Test
    @DisplayName("차량 조회 메서드")
    void findByBrandShouldReturnCar() {
        // given
        Owner owner = new Owner("동차", "차");
        ownerRepository.save(owner);
        Car car = new Car("Ford", "Mustang", "Red", "ABCDEF", 2021, 567890, owner);
        carRepository.save(car);

        carRepository.save(new Car("Toyota", "Treno", "blue", "ABCDEF", 2022, 567890, owner));
        carRepository.save(new Car("Toyota", "GR86", "blue", "ABCDEF", 2025, 567890, owner));
        // when -> carRepository.findByBrand("브랜드명") -> 얘의 자료형
        // list라는 것을 확인할 수 있는데,
        List<Car> toyotas = carRepository.findByBrand("Toyota");

        // then에서의 검증은 list 내부의 element의 자료형이 Car 객체일테니까,
        // 그 객체의 getBrand()의 결과값이 우리가 findByBrand()의 argument로 쓴 값과 동일한지를 체크할 수 있겠다.
        // list에서 element 하나만 꺼낼 수 있는 메서드 -> .size()
        assertThat(toyotas.get(0).getBrand()).isEqualTo("Toyota");
        // 혹은, 현재 저희가 Toyota 차량을 두 대 만들었으니까 size()의 결과값이 2겠지.
        assertThat(toyotas.size()).isEqualTo(2);

    }
}
