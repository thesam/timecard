package se.timberline.timecard.model;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.persistence.EntityManager;

@Component
public class DayRepository {

    @Autowired
    private EntityManager entityManager;

    public void store(Day day) {
        entityManager.persist(day);
    }
}
