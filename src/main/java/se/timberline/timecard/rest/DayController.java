package se.timberline.timecard.rest;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import se.timberline.timecard.model.Day;
import se.timberline.timecard.model.DayRepository;

import java.time.LocalDate;

@RestController
@RequestMapping("/day")
public class DayController {

    private Logger logger = Logger.getLogger(DayController.class);

    @Autowired
    private DayRepository dayRepository;

    @RequestMapping(method = RequestMethod.GET)
    public Day findByDate(@RequestParam String date) {
        return dayRepository.findByDate(LocalDate.parse(date));
    }

    @RequestMapping(method = RequestMethod.POST)
    public Day save(@RequestBody Day day) {
        if (day.id() != null) {
            Day savedDay = dayRepository.findOne(day.id());
            savedDay.update(day);
            return savedDay;
        } else {
            return dayRepository.save(day);
        }
    }

    @ExceptionHandler
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public void handle(HttpMessageNotReadableException e) {
        logger.warn("Returning HTTP 400 Bad Request", e);
    }
}
