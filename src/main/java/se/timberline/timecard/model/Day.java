package se.timberline.timecard.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.time.LocalDate;

@Entity
public class Day {

    @Id
    @GeneratedValue
    private Long id;

    @JsonProperty
    @Column
    private LocalDate date;

    public Long id() {
        return id;
    }

    public LocalDate date() {
        return date;
    }
}
