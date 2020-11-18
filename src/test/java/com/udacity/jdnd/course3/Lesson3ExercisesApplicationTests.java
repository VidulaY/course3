package com.udacity.jdnd.course3;

import com.udacity.jdnd.course3.entity.Delivery;
import com.udacity.jdnd.course3.entity.Plant;
import com.udacity.jdnd.course3.repository.PlantRepository;
import org.assertj.core.util.Lists;
import org.h2.table.Plan;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;


@DataJpaTest
public class Lesson3ExercisesApplicationTests {
    @Autowired
    TestEntityManager testEntityManager;

    @Autowired
    PlantRepository plantRepository;

    @Test
    public void testPriceLessThan(){
        Plant p = testEntityManager.persist(new Plant("Orchid", BigDecimal.valueOf(4.99)));
        testEntityManager.persist(new Plant("Rose", BigDecimal.valueOf(5.01)));
        List<Plant> cheapPlants = plantRepository.findByPriceLessThan(BigDecimal.valueOf(5));
        Assertions.assertEquals(1, cheapPlants.size(), "Size");
        Assertions.assertEquals(p.getId(), cheapPlants.get(0).getId(), "Id");
    }

    @Test
    public void testDeliveryCompleted(){

        Plant p = testEntityManager.persist(new Plant("Baz Root", BigDecimal.valueOf(9.99)));
        Delivery d = testEntityManager.persist(new Delivery("Leonard Bernstein", "234 West Side", LocalDateTime.now()));
        List<Plant> list = new ArrayList<>();
        list.add(p);
        System.out.println(list);
        d.setPlants(list);
        p.setDelivery(d);

        //test both before and after
        Assertions.assertFalse(plantRepository.deliveryCompleted(p.getId()));
        d.setCompleted(true);
        Assertions.assertTrue(plantRepository.deliveryCompleted(p.getId()));
    }

}
