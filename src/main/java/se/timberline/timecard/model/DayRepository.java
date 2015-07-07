package se.timberline.timecard.model;

import org.springframework.data.repository.Repository;
import org.springframework.stereotype.Component;

@Component
public interface DayRepository extends Repository<Day, Long> {
    Day save(Day day);
}
