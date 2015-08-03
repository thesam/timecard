package se.timberline.timecard.model;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;
import se.timberline.timecard.Application;

import javax.persistence.EntityManager;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

@SpringApplicationConfiguration(classes = Application.class)
public class DayRepositoryIntegrationTest extends AbstractTransactionalJUnit4SpringContextTests {

    @Autowired
    private DayRepository dayRepository;

    @Autowired
    private EntityManager entityManager;

    @Test
    public void canStore() {
        Day day = new Day();
        Day savedDay = dayRepository.save(day);
        assertEquals(day.date(), savedDay.date());
        assertNotNull(savedDay.id());
    }

}