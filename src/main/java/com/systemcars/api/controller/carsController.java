package com.systemcars.api.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.systemcars.api.entities.Car;
import com.systemcars.api.entities.User;
import com.systemcars.api.repository.CarRepository;
import com.systemcars.api.repository.UserRepository;
import com.systemcars.api.service.CarService;

@RestController
@RequestMapping("/cars")
public class carsController {

    private final CarService carService;
    private final UserRepository userRepository;
    private final CarRepository carRepository;

    public carsController(CarService carService, UserRepository userRepository, CarRepository carRepository) {
        this.carService = carService;
        this.userRepository = userRepository;
        this.carRepository = carRepository;
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

    // ======================= Criar carro para usuário =======================

    @PostMapping("/users/{userId}/cars")
    public ResponseEntity<Car> createCarForUser(
            @PathVariable Long userId,
            @RequestBody Car carRequest) {

        Optional<User> userOpt = userRepository.findById(userId);

        if (userOpt.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        User user = userOpt.get();
        carRequest.setUser(user);

        Car savedCar = carService.createCar(carRequest);

        return ResponseEntity.ok(savedCar);
    }

    // ======================= Atualizar carro de usuário =======================

    @PutMapping("/users/{userId}/cars/{carId}")
    public ResponseEntity<Car> updateCar(
            @PathVariable Long userId,
            @PathVariable Long carId,
            @RequestBody Car updatedCar) {

        Optional<User> userOpt = userRepository.findById(userId);
        if (userOpt.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        Optional<Car> carOpt = carRepository.findById(carId);
        if (carOpt.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        Car car = carOpt.get();

        if (!car.getUser().getId().equals(userId)) {
            return ResponseEntity.badRequest().build();
        }

        car.setMarca(updatedCar.getMarca());
        car.setModelo(updatedCar.getModelo());

        Car savedCar = carService.updateCar(car);

        return ResponseEntity.ok(savedCar);
    }
}
