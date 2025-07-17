package com.example.paris_janitor_api.adapters.out.persistence;

import com.example.paris_janitor_api.core.model.Booking;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookingMongoRepo extends MongoRepository<Booking, String> {
    List<Booking> findBookingByPropertyId(String propertyId);
}
