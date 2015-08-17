package se.timberline.timecard.model;

import org.springframework.data.repository.Repository;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
public interface DayRepository extends Repository<Day, Long> {
    Day save(Day day);

    Day findOne(Long id);

    Day findByDate(LocalDate date);
}
