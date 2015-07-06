package se.timberline.timecard.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.Column;
import javax.persistence.Entity;
import java.time.LocalDate;

@Entity
public class Day {

    @JsonProperty
    @Column
    private LocalDate date;

    public LocalDate date() {
        return date;
    }
}
