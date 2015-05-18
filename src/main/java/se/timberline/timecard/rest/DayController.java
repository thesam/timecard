package se.timberline.timecard.rest;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import se.timberline.timecard.model.Day;

@RestController
@RequestMapping("/day")
public class DayController {

    @RequestMapping(method = RequestMethod.GET)
    public String foo() {
        return "TEST";
    }

    @RequestMapping(method = RequestMethod.POST)
    public void save(Day day) {
        System.err.println(day.date());
    }
}
