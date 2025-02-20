package com.example.paris_janitor_api.framework.repository;


import com.example.paris_janitor_api.framework.db.BookingDocument;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface SpringDataMongoBookingRepository extends MongoRepository<BookingDocument,String> {
}
