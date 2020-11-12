package com.udacity.jdnd.course3.service;

import com.udacity.jdnd.course3.entity.Delivery;
import com.udacity.jdnd.course3.repository.DeliveryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DeliveryService {
    @Autowired
    DeliveryRepository deliveryRepository;

    @Autowired
    PlantService plantService;

    public Long save(Delivery delivery){
        delivery.getPlants().forEach(plant -> plant.setDelivery(delivery));
        deliveryRepository.persist(delivery);
        return delivery.getId();
    }
}
