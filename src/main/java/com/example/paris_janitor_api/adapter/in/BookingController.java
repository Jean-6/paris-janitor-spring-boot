package com.example.paris_janitor_api.adapter.in;

//import com.example.paris_janitor_api.service.BookingService;
import lombok.extern.log4j.Log4j2;
        import org.springframework.web.bind.annotation.*;

@Log4j2
@RestController
@RequestMapping("/api/booking")
public class BookingController {

   /* @Autowired
    private BookingService bookingService;


    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Optional<Booking>> getBookingById(@RequestParam("id") String id) {
        return ResponseEntity.status(HttpStatus.OK).body(bookingService.getBookingById(id));
    }

    @GetMapping(value = "/user", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Booking[]> getBookingByUserId(@RequestParam String userId) {
        if(userId==null || userId.isEmpty()){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new Booking[0]);
        }
        try{
            List<Booking> bookings = bookingService.getBookingByUserId(userId);
            return ResponseEntity.status(HttpStatus.OK).body(bookings.toArray(new Booking[0]));
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new Booking[0]);
        }

    }

    @GetMapping(value="/by-week-and-year/{id}",produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Booking>>getBookingByWeekAndYear(@PathVariable("id") String id,
                                                                   @RequestParam("week") String week,
                                                                   @RequestParam("year") String year) {
        try{
            List<Booking> bookings= bookingService.getBookingBy(id, week, year);
            return ResponseEntity.status(HttpStatus.OK).body(bookings);
        }catch (BadRequestException badEx){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }
    }

    @PostMapping(value="/",consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Booking> save(@RequestBody Booking booking) {
        try{
            Booking bookingCreated= bookingService.saveBooking(booking);
            return ResponseEntity.status(HttpStatus.CREATED).body(bookingCreated);
        }catch (BadRequestException badEx){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }
    }

    @GetMapping(value = "/",produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Booking>> getBookings() {
        try{
            List<Booking> bookingList=bookingService.getBookings();
            if(bookingList.isEmpty()) {
                return ResponseEntity.status(HttpStatus.NO_CONTENT).body(bookingList);
            }
            return ResponseEntity.status(HttpStatus.OK).body(bookingList);
        }catch(BadRequestException badEx){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }
    }

    @DeleteMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public  ResponseEntity<?> deleteBooking(@PathVariable String id) {
        try{
            Optional<Booking> booking = bookingService.deleteBooking(id);
            if(booking.isPresent()){
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
            }
            return ResponseEntity.status(HttpStatus.OK).body("Reservation d'Id : "+booking.get().getId()+" a été supprimé");
        }catch(BadRequestException badEx){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }
    }*/
}
