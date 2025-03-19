package com.example.paris_janitor_api.adapters.in;


import com.example.paris_janitor_api.application.port.in.booking.*;
import com.example.paris_janitor_api.core.model.Booking;
import com.example.paris_janitor_api.infrastructure.exception.GenericException;
import com.example.paris_janitor_api.infrastructure.exception.IllegalArgumentException;
import com.example.paris_janitor_api.infrastructure.exception.ResourceNotFoundException;
import lombok.extern.log4j.Log4j2;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;


@Log4j2
@RestController
@RequestMapping("/api/booking")
public class BookingController {

    private final DeleteBookingByIdPort deleteBookingByIdPort;
    private final LoadAllBookingsPort loadAllBookingsPort;
    private final LoadBookingByIdPort loadBookingByIdPort;
    private final PersistBookingPort persistBookingPort;
    private final UpdateBookingPort updateBookingPort;


    static Logger logger = LoggerFactory.getLogger(BookingController.class);

    public BookingController(DeleteBookingByIdPort deleteBookingByIdPort, LoadAllBookingsPort loadAllBookingsPort, LoadBookingByIdPort loadBookingByIdPort, PersistBookingPort persistBookingPort, UpdateBookingPort updateBookingPort) {
        this.deleteBookingByIdPort = deleteBookingByIdPort;
        this.loadAllBookingsPort = loadAllBookingsPort;
        this.loadBookingByIdPort = loadBookingByIdPort;
        this.persistBookingPort = persistBookingPort;
        this.updateBookingPort = updateBookingPort;
    }


    @PostMapping(value="/",consumes = MediaType.APPLICATION_JSON_VALUE)
    public Mono<ResponseEntity<Booking>> save(@RequestBody Booking booking) {

        try{
            if(booking.getUserId().isBlank()) {
                throw new IllegalArgumentException("Test");
            }
            return persistBookingPort.saveBooking(booking)
                    .map(saveBooking -> ResponseEntity.status(HttpStatus.CREATED)
                            .body(saveBooking))
                    .onErrorResume(err -> { //erreur se produisant dans la chaine reactive
                        logger.error("Error saving booking", err);
                        return Mono.error(new GenericException(err.getMessage()));
                    });
        } catch (Exception e){ // Tout autre erreur de maniere globale
            logger.error(e.getMessage());
            return Mono.error(new GenericException(e.getMessage()));
        }
    }


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
                        logger.error("Error getting booking", error);
                        return Mono.error(new GenericException(error.getMessage()));
                    });
        }catch (Exception e){
            return Mono.error(new GenericException(""));

        }
    }

    @GetMapping(value = "/",produces = MediaType.APPLICATION_JSON_VALUE)
    public Flux<ResponseEntity<Booking>> getBookings() {

            return loadAllBookingsPort.getAllBookings()
                    .map(booking -> ResponseEntity.ok().body(booking))
                    .onErrorResume(err->{
                        logger.error("Error getting bookings",err);
                        return Mono.error(new GenericException(err.getMessage()));
                    });

    }

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
            logger.error(ex.getMessage());
            return Mono.error(new GenericException(ex.getMessage()));
        }
    }

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
            logger.error(e.getMessage());
            return Mono.error(new GenericException(e.getMessage()));
        }
    }

}
