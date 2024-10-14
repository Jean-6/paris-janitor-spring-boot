package com.example.paris_janitor_api.repository;


import com.example.paris_janitor_api.model.Booking;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BookingRepository extends MongoRepository<Booking, String> {
    @Override
    void deleteById(String s);
}