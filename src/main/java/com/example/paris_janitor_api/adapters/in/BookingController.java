package com.example.paris_janitor_api.adapters.in;


import com.example.paris_janitor_api.application.port.in.booking.*;
import com.example.paris_janitor_api.core.model.Booking;
import com.example.paris_janitor_api.infrastructure.web.ResponseWrapper;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.HandlerMapping;

import java.util.List;


@Slf4j
@RestController
@RequestMapping("/api/booking")
@Tag(name = "Booking API", description = "Booking management")
public class BookingController {

    private final DeleteBookingByIdPort deleteBookingByIdPort;
    private final LoadAllBookingsPort loadAllBookingsPort;
    private final LoadBookingByIdPort loadBookingByIdPort;
    private final PersistBookingPort persistBookingPort;
    private final UpdateBookingPort updateBookingPort;

    public BookingController(DeleteBookingByIdPort deleteBookingByIdPort, LoadAllBookingsPort loadAllBookingsPort, LoadBookingByIdPort loadBookingByIdPort, PersistBookingPort persistBookingPort, UpdateBookingPort updateBookingPort) {
        this.deleteBookingByIdPort = deleteBookingByIdPort;
        this.loadAllBookingsPort = loadAllBookingsPort;
        this.loadBookingByIdPort = loadBookingByIdPort;
        this.persistBookingPort = persistBookingPort;
        this.updateBookingPort = updateBookingPort;
    }


    @Operation(summary = "Create a new booking", description = "Save a new booking in the system")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Booking successfully created"),
            @ApiResponse(responseCode = "400", description = "Invalid booking data",
                    content = @Content(schema = @Schema())),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    @PostMapping(value="/",consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ResponseWrapper<Booking>> save(@RequestBody Booking booking, HttpServletRequest request) {

        Booking bookingSaved = persistBookingPort.saveBooking(booking);
        return ResponseEntity.ok(
                ResponseWrapper.ok("",request.getRequestURI(),bookingSaved));
    }


    @Operation(summary = "Fetch property bookings", description = "Retrieve all bookings associated with a given property ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Bookings successfully retrieved"),
            @ApiResponse(responseCode = "400", description = "Provided property ID is invalid",
                    content = @Content(schema = @Schema())),
            @ApiResponse(responseCode = "404", description = "No bookings found for the given property ID",
                    content = @Content(schema = @Schema())),
            @ApiResponse(responseCode = "500", description = "Unexpected server error occurred")
    })
    @GetMapping(value = "property/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ResponseWrapper<List<Booking>>> getBookingsByPropertyId(@PathVariable("id") String id, HttpServletRequest request) {

        List<Booking> bookings = loadBookingByIdPort.getBookingByPropertyId(id);
        return ResponseEntity.ok(
                ResponseWrapper.ok("",request.getRequestURI(),bookings));
    }


    @Operation(summary = "Retrieve a booking by ID", description = "Get a booking from the system using its unique ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Booking retrieved successfully"),
            @ApiResponse(responseCode = "400", description = "Invalid ID provided",
                    content = @Content(schema = @Schema())),
            @ApiResponse(responseCode = "404", description = "Booking not found",
                    content = @Content(schema = @Schema())),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ResponseWrapper<Booking>> getBookingById(@RequestParam("id") String id, HttpServletRequest request) {

        Booking booking = loadBookingByIdPort.getBookingById(id);
        return ResponseEntity.ok(
                ResponseWrapper.ok("",request.getRequestURI(),booking));
    }

    @Operation(summary = "Retrieve all bookings", description = "Get all bookings stored in the system")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Bookings retrieved successfully"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    @GetMapping(value = "/",produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ResponseWrapper<List<Booking>>> getBookings(HttpServletRequest request) {

        List<Booking> bookings = loadAllBookingsPort.getAllBookings();
        return ResponseEntity.ok(
                ResponseWrapper.ok("",request.getRequestURI(),bookings));
    }

    @Operation(summary = "Delete a booking by ID", description = "Remove a booking from the system using its unique ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Booking deleted successfully"),
            @ApiResponse(responseCode = "404", description = "Booking not found",
                    content = @Content(schema = @Schema())),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    @DeleteMapping(value="/{id}",produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ResponseWrapper<Booking>> delete(@PathVariable("id") String id, HttpServletRequest request) {
        deleteBookingByIdPort.deleteById(id);
        return ResponseEntity.ok(
                ResponseWrapper.ok("Reservation  supprimée ",request.getRequestURI()));
    }

    @Operation(summary = "Update a booking by ID", description = "Modify an existing booking in the system using its unique ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Booking updated successfully"),
            @ApiResponse(responseCode = "400", description = "Invalid booking data or empty ID",
                    content = @Content(schema = @Schema())),
            @ApiResponse(responseCode = "404", description = "Booking not found",
                    content = @Content(schema = @Schema())),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    @PutMapping(value="/{id}",produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ResponseWrapper<Booking>> update(@PathVariable("id") String id, @RequestBody Booking booking, HttpServletRequest request) {

        Booking booking1=updateBookingPort.updateBooking(id,booking);
        return ResponseEntity.ok(
                ResponseWrapper.ok("",request.getRequestURI(),booking1));
    }

}
