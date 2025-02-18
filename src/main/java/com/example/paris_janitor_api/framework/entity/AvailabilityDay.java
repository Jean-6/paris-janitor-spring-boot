package com.example.paris_janitor_api.framework.entity;

import java.time.LocalDate;
import java.util.List;

public class AvailabilityDay {
    private int weekNumber;
    private long year;
    private LocalDate date; // Le jour de disponibilité
    private List<AvailabilityHour> availabilities; //
}
