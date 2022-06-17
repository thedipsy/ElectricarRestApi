package com.electricar.electricarrestapi.web

import com.electricar.electricarrestapi.model.entity.Car
import com.electricar.electricarrestapi.service.interfaces.ICarService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/cars")
@CrossOrigin
class CarController(private val carService: ICarService) {

    @GetMapping
    fun getAllCars() : ResponseEntity<List<Car>>
        = ResponseEntity(carService.getAll(), HttpStatus.OK)

    @GetMapping("/{id}")
    fun getCarById(@PathVariable id: Long) : ResponseEntity<Car>
        = ResponseEntity.ok(carService.get(id))

    @PostMapping
    fun createCar(@RequestBody car: Car) : ResponseEntity<Car>
        = ResponseEntity.ok(carService.insert(car))

    @PutMapping("/{id}")
    fun updateCar(@PathVariable id: Long, @RequestBody car: Car) : ResponseEntity<Car>
        = ResponseEntity.ok(carService.update(car, id))

    @DeleteMapping("/{id}")
    fun deleteCar(@PathVariable id:Long) : ResponseEntity<Unit>
        = ResponseEntity.ok(carService.delete(id))

}