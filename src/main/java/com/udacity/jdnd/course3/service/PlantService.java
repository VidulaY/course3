package com.udacity.jdnd.course3.service;

import com.udacity.jdnd.course3.entity.Plant;
import org.springframework.stereotype.Service;

@Service
public class PlantService {
    public Plant getPlantByName(String name){
        return new Plant();
    }
}
