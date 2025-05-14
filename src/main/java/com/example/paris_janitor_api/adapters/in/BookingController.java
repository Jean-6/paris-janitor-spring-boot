package com.example.paris_janitor_api.adapters.in;


import com.example.paris_janitor_api.application.port.in.booking.*;
import com.example.paris_janitor_api.core.model.Booking;
import com.example.paris_janitor_api.infrastructure.exception.GenericException;
import com.example.paris_janitor_api.infrastructure.exception.IllegalArgumentException;
import com.example.paris_janitor_api.infrastructure.exception.ResourceNotFoundException;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

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
    public Mono<ResponseEntity<List<Booking>>> save(@RequestBody Flux<Booking> bookings) {

        return persistBookingPort.saveBooking(bookings)
                .collectList()
                .map(savedBookings -> ResponseEntity.status(HttpStatus.OK).body(savedBookings))
                .defaultIfEmpty(ResponseEntity.badRequest().build())
                .onErrorResume(err->{
                    log.error(err.getMessage());
                    return Mono.just(ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                            .build());
                });
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
    public Mono<ResponseEntity<List<Booking>>> getBookingsByPropertyId(@PathVariable("id") String id) {

        if (id.isBlank()) {
            return Mono.just(ResponseEntity.badRequest().build());
        }
        return loadBookingByIdPort.getBookingByPropertyId(id)
                .collectList()
                .map(bookings -> {
                    if (bookings.isEmpty()) {
                        return ResponseEntity.status(HttpStatus.NOT_FOUND).<List<Booking>>build();
                    }
                    return ResponseEntity.ok(bookings);
                })
                .defaultIfEmpty(ResponseEntity.notFound().build());
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
    public Mono<ResponseEntity<Booking>> getBookingById(@RequestParam("id") String id) {
        try {
            if (id.isBlank()) {
                return Mono.error(new IllegalArgumentException("id ne peut être vide"));
            }
            return loadBookingByIdPort.getBookingById(id)
                    .map(booking -> ResponseEntity.ok().body(booking))
                    .switchIfEmpty(Mono.error(new ResourceNotFoundException("Booking not found")))
                    .onErrorResume(error->{
                        log.error("Error getting booking", error);
                        return Mono.error(new GenericException(error.getMessage()));
                    });
        }catch (Exception e){
            return Mono.error(new GenericException(""));
        }
    }

    @Operation(summary = "Retrieve all bookings", description = "Get all bookings stored in the system")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Bookings retrieved successfully"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    @GetMapping(value = "/",produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Flux<Booking>> getBookings() {

        Flux<Booking> bookingFlux = loadAllBookingsPort.getAllBookings()
                .doOnNext(booking ->{log.info(booking.toString());})
                .onErrorResume(err->{
                    log.error("Error getting bookings", err);
                    return Flux.error(new GenericException(err.getMessage()));
                });
        return ResponseEntity.status(HttpStatus.OK)
                .body(bookingFlux);
    }

    @Operation(summary = "Delete a booking by ID", description = "Remove a booking from the system using its unique ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Booking deleted successfully"),
            @ApiResponse(responseCode = "404", description = "Booking not found",
                    content = @Content(schema = @Schema())),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    @DeleteMapping(value="/{id}",produces = MediaType.APPLICATION_JSON_VALUE)
    public Mono<ResponseEntity<Void>> delete(@PathVariable("id") String id) {

        try {
            if (id.isBlank()) {
                // return Mono.error(new IllegalArgumentException("id ne peut pas être null ou vide")); // Gestion des erreurs invalides
                throw new IllegalArgumentException("id ne peut etre null");
            }
            return deleteBookingByIdPort.deleteById(id)
                    .then(Mono.just(ResponseEntity.ok().build()));
        }catch (Exception ex){
            log.error(ex.getMessage());
            return Mono.error(new GenericException(ex.getMessage()));
        }
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
    public Mono<ResponseEntity<Booking>> update(@PathVariable("id") String id, @RequestBody Booking booking) {

        try{
            if (id.isBlank()) {
                throw new IllegalArgumentException("Test");
            }
            Booking newBooking = new Booking();
            return updateBookingPort.updateBooking(id,newBooking)
                    .map(bookingUpdated->ResponseEntity.ok()
                            .body(bookingUpdated))
                    .defaultIfEmpty(ResponseEntity.notFound().build())
                    .onErrorResume(err->{
                        return Mono.error(new GenericException(err.getMessage()));
                    });
        }catch (Exception e){
            log.error(e.getMessage());
            return Mono.error(new GenericException(e.getMessage()));
        }
    }

}
