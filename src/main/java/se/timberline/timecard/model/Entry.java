package se.timberline.timecard.model;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class Entry {
    @Column
    public String start;
    @Column
    public String stop;
}
