package com.example.paris_janitor_api.adapters.out.persistence;

import com.example.paris_janitor_api.core.model.Booking;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BookingReactiveMongoRepo extends ReactiveMongoRepository<Booking, String> {
}
