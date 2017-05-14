package se.timberline.timecard.model;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import se.timberline.timecard.Application;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceException;
import java.time.LocalDate;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.fail;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class)
@WebAppConfiguration
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