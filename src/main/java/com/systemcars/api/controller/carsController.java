
package com.systemcars.api.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.systemcars.api.entities.Car;
import com.systemcars.api.service.CarService;

@RestController
@RequestMapping("/cars")
public class carsController {

    private final CarService carService;

    public carsController(CarService carService) {
        this.carService = carService;
    }

    @GetMapping
    public List<Car> getAllCars() {
        return carService.getAllCars();
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<Car>> getCarsByUser(@PathVariable Long userId) {
        List<Car> cars = carService.getCarsByUserId(userId);
        if (cars == null || cars.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(cars);
    }
}
// ...existing code...
