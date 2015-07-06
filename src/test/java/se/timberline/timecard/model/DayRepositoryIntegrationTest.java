package se.timberline.timecard.model;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;

public class DayRepositoryIntegrationTest extends AbstractTransactionalJUnit4SpringContextTests {

    @Autowired
    private DayRepository dayRepository;

    @Test
    public void canStore() {
        dayRepository.store(new Day());
    }

}