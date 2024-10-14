package com.example.paris_janitor_api.service.impl;

import com.example.paris_janitor_api.model.Booking;
import com.example.paris_janitor_api.repository.BookingRepository;
import com.example.paris_janitor_api.service.BookingService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class BookingServiceImpl  implements BookingService {

    @Autowired
    private MongoTemplate mongoTemplate;

    @Autowired
    private BookingRepository bookingRepository;

    @Override
    public Booking saveBooking(Booking booking) {
        return bookingRepository.save(booking);
    }

    @Override
    public List<Booking> getBookings() {
        return bookingRepository.findAll();
    }

    @Override
    public Optional<Booking> deleteBooking(String id) {
        Query query = new Query();
        query.addCriteria(new Criteria().andOperator(Criteria.where("id").is(id)));
        return Optional.ofNullable(mongoTemplate.findAndRemove(new Query(), Booking.class));
    }

    @Override
    public List<Booking> getBookingBy(String id, String week, String year) {
        Query query = new Query();
        Criteria criteria = new Criteria().andOperator(Criteria.where("propertyId").is(id),
                Criteria.where("weekNumber").is(week),
                Criteria.where("year").is(year));
        query.addCriteria(criteria);
        return mongoTemplate.find(query, Booking.class);
    }

    @Override
    public List<Booking> getBookingByUserId(String userId) {
        /*Query query = new Query();
        Criteria criteria = new Criteria();
        criteria.andOperator(Criteria.where("userId").is(userId));
        query.addCriteria(criteria);
        return mongoTemplate.find(query, Booking.class);*/

        // Créer un critère de recherche pour le userId
        Query query = new Query();
        query.addCriteria(Criteria.where("userId").is(userId));

        // Exécuter la requête et récupérer la liste des bookings
        return mongoTemplate.find(query, Booking.class);
    }

    @Override
    public Optional<Booking> getBookingById(String id) {
        return Optional.empty();
    }

}
