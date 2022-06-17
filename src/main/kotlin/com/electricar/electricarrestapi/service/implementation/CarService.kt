package com.electricar.electricarrestapi.service.implementation

import com.electricar.electricarrestapi.model.entity.Car
import com.electricar.electricarrestapi.model.exception.CarNotFoundException
import com.electricar.electricarrestapi.repository.ICarRepository
import com.electricar.electricarrestapi.service.interfaces.ICarService
import org.springframework.stereotype.Service

@Service
class CarService(private val carRepository: ICarRepository) : ICarService {

    override fun getAll(): List<Car> = carRepository.findAll()

    override fun get(id: Long): Car = carRepository.findById(id).orElseThrow()

    override fun insert(car: Car) : Car = carRepository.save(car)

    override fun update(car: Car, id: Long): Car {
        return if(carRepository.existsById(id)){
            carRepository.save(car)
        }
        else throw CarNotFoundException("Car not found.")
    }

    override fun delete(id: Long) {
        if(carRepository.existsById(id)){
            carRepository.deleteById(id)
        } else throw CarNotFoundException("Car not found.")
    }
}