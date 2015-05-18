package se.timberline.timecard.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.time.LocalDate;

public class Day {

    @JsonProperty
    private LocalDate date;

    public LocalDate date() {
        return date;
    }
}
