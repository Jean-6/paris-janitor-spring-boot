package com.example.paris_janitor_api.controller;

import com.example.paris_janitor_api.model.Booking;
import com.example.paris_janitor_api.service.BookingService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Log4j2
@RestController
@RequestMapping("/api/booking")
public class BookingController {

    @Autowired
    private BookingService bookingService;

    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Optional<Booking>> getBookingById(@RequestParam("id") String id) {
        return ResponseEntity.status(HttpStatus.OK).body(bookingService.getBookingById(id));
    }

    @PostMapping(value="/",consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Booking> save(@RequestBody Booking booking) {
        return  ResponseEntity.status(HttpStatus.OK).body(bookingService.saveBooking(booking));
    }

    @GetMapping(value = "/",produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Iterable<Booking>> getBookings() {
        return ResponseEntity.status(HttpStatus.OK).body(bookingService.getBookings());
    }

    @DeleteMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public  ResponseEntity<Void> deleteBooking(@RequestParam("id") String id) {
        try{
            bookingService.deleteBooking(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }catch (Exception e){
            return new ResponseEntity<>(HttpStatus.UNPROCESSABLE_ENTITY);
        }
    }
}
