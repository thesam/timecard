package se.timberline.timecard.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;

import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.time.LocalDate;
import java.util.List;

@Entity
public class Day {

    @Id
    @GeneratedValue
    @JsonProperty
    private Long id;

    @JsonProperty
    @JsonDeserialize(using = LocalDateDeserializer.class)
    @JsonSerialize(using = LocalDateSerializer.class)
    @Column
    private LocalDate date;

    @JsonProperty
    @ElementCollection
    private List<Entry> entries;

    public Day(LocalDate date) {
        this.date = date;
    }

    public Day() {
    }

    public Long id() {
        return id;
    }

    public LocalDate date() {
        return date;
    }

    public void update(Day day) {
        this.date = day.date;
    }
}
