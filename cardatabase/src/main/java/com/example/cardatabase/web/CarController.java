package com.example.cardatabase.web;

import com.example.cardatabase.domain.Car;
import com.example.cardatabase.domain.CarRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CarController {
    private final CarRepository carRepository; // 마음대로 바뀌게 하지 않도록 final을 작성함

    public CarController(CarRepository carRepository) {
        this.carRepository = carRepository;
    }

    @GetMapping("/cars")
    public Iterable<Car> getCars(){
        return carRepository.findAll();
    }
}