package com.codeclan.example.CourseBooking.components;

import com.codeclan.example.CourseBooking.models.Booking;
import com.codeclan.example.CourseBooking.models.Course;
import com.codeclan.example.CourseBooking.models.Customer;
import com.codeclan.example.CourseBooking.repositories.BookingRepository;
import com.codeclan.example.CourseBooking.repositories.CourseRepository;
import com.codeclan.example.CourseBooking.repositories.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

@Component
public class DataLoader implements ApplicationRunner {

    @Autowired
    CustomerRepository customerRepository;

    @Autowired
    BookingRepository bookingRepository;

    @Autowired
    CourseRepository courseRepository;

    public void run(ApplicationArguments args){

        Customer customer = new Customer("Claire", "Leith", 30);
        customerRepository.save(customer);

        Customer customer1 = new Customer("David", "Musselburgh", 50);
        customerRepository.save(customer1);

        Course course = new Course("Codeclan", "Edinburgh", 4);
        courseRepository.save(course);

        Course course1 = new Course("DataScience", "Glasgow", 2);
        courseRepository.save(course1);

        Booking booking = new Booking("12-09-22", course, customer);
        bookingRepository.save(booking);

        Booking booking1 = new Booking("15-09-22", course1, customer1);
        bookingRepository.save(booking1);

        Booking booking2 = new Booking("12-09-22", course1, customer);
        bookingRepository.save(booking2);

    }
}
