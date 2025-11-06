package com.systemcars.api.repository;
import org.springframework.data.jpa.repository.JpaRepository;

import com.systemcars.api.entities.Car;

import java.util.List;

public interface CarRepository extends JpaRepository<Car, Long> {
    List<Car> findByUserId(Long userId);
}
