package com.udacity.jdnd.course3.service;

import com.udacity.jdnd.course3.entity.Plant;
import com.udacity.jdnd.course3.repository.PlantRepository;
import org.hibernate.annotations.Check;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
public class PlantService {
    @Autowired
    PlantRepository plantRepository;

//    Save a new Plant and return its Id
//    Check if a plant has been delivered
//    Find a list of plants cheaper than a specified amount
    public Plant getPlantByName(String name){
        return new Plant();
    }

    public Long save(Plant plant){
        return plantRepository.save(plant).getId();
    }

    public Boolean delivered(Long id){
        return plantRepository.deliveryCompleted(id);
    }

    public List<Plant> findPlantsBelowPrice(BigDecimal price){
        return plantRepository.findByPriceLessThan(price);
    }
}
