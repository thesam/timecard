package se.timberline.timecard.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import se.timberline.timecard.model.Day;
import se.timberline.timecard.model.DayRepository;

@RestController
@RequestMapping("/day")
public class DayController {

    @Autowired
    private DayRepository dayRepository;

    @RequestMapping(method = RequestMethod.GET)
    public String foo() {
        return "TEST";
    }

    @RequestMapping(method = RequestMethod.POST)
    public void save(Day day) {
        dayRepository.save(day);
    }
}
