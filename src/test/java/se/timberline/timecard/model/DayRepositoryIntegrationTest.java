package se.timberline.timecard.model;

import org.hibernate.exception.ConstraintViolationException;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;
import se.timberline.timecard.Application;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceException;
import java.io.IOException;
import java.time.LocalDate;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.fail;

@SpringApplicationConfiguration(classes = Application.class)
public class DayRepositoryIntegrationTest extends AbstractTransactionalJUnit4SpringContextTests {

    @Autowired
    private DayRepository dayRepository;

    @Autowired
    private EntityManager entityManager;

    @Test
    public void canStore() {
        Day day = new Day(LocalDate.parse("2015-02-01"));
        dayRepository.save(day);
        entityManager.flush();
        entityManager.clear();
        Day savedDay = dayRepository.findByDate(LocalDate.parse("2015-02-01"));
        assertEquals(day.date(), savedDay.date());
        assertNotNull(savedDay.id());
    }

    @Test
    public void requiresUniqueDate() {
        Day day = new Day(LocalDate.parse("2015-02-01"));
        Day day2 = new Day(LocalDate.parse("2015-02-01"));
        dayRepository.save(day);
        entityManager.flush();
        try {
            dayRepository.save(day2);
            entityManager.flush();
            fail("Should throw exception!");
        } catch (PersistenceException pe) {

        }
    }

}