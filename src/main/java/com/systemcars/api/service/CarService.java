package com.systemcars.api.service;

import com.systemcars.api.entities.Car;
import com.systemcars.api.repository.CarRepository;

import java.util.List;

import org.springframework.stereotype.Service;

@Service
public class CarService {
    private final CarRepository carRepository;

    public CarService(CarRepository carRepository) {
        this.carRepository = carRepository;
    }

    public List<Car> getAllCars() {
        return carRepository.findAll();
    }

    public List<Car> getCarsByUserId(Long userId) {
        return carRepository.findByUserId(userId);
    }
}
