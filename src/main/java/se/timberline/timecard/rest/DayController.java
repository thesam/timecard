package se.timberline.timecard.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import se.timberline.timecard.model.Day;
import se.timberline.timecard.model.DayRepository;

import java.time.LocalDate;

@RestController
@RequestMapping("/day")
public class DayController {

    @Autowired
    private DayRepository dayRepository;

    @RequestMapping(method = RequestMethod.GET)
    public Day findByDate(@RequestParam String date) {
        return dayRepository.findByDate(LocalDate.parse(date));
    }

    @RequestMapping(method = RequestMethod.POST)
    public void save(@RequestBody Day day) {
        dayRepository.save(day);
    }
}
