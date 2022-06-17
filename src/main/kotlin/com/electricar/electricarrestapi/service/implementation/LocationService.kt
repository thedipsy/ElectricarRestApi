package com.electricar.electricarrestapi.service.implementation

import com.electricar.electricarrestapi.model.entity.Location
import com.electricar.electricarrestapi.model.exception.LocationNotFoundException
import com.electricar.electricarrestapi.repository.ILocationRepository
import com.electricar.electricarrestapi.service.interfaces.ILocationService
import org.springframework.stereotype.Service

@Service
class LocationService(private val locationRepository: ILocationRepository) : ILocationService {

    override fun getAll(): List<Location> = locationRepository.findAll()

    override fun get(id: Long): Location = locationRepository.findById(id).orElseThrow()


    override fun insert(location: Location): Location = locationRepository.save(location)

    override fun update(location: Location, id: Long): Location {
        return if(location.id == id && locationRepository.existsById(id)){
            locationRepository.save(location)
        }
        else throw LocationNotFoundException(String.format("Location with id %d is not found", id))
    }

    override fun delete(id: Long) {
        if(locationRepository.existsById(id)){
            locationRepository.deleteById(id)
        }
        throw LocationNotFoundException(String.format("Location with id %d is not found", id))
    }

}