package com.codeclan.example.CourseBooking.controllers;

import com.codeclan.example.CourseBooking.models.Course;
import com.codeclan.example.CourseBooking.repositories.CourseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class CourseController {

    @Autowired
    CourseRepository courseRepository;

    @GetMapping(value = "/courses")
    public ResponseEntity<List<Course>> getAllCourseOrCoursesByFilter(
            @RequestParam(name = "rating", required = false) Integer rating,
            @RequestParam(name ="customerName", required = false) String customerName
    ){
        if(rating == null && customerName == null){
            return new ResponseEntity<>(courseRepository.findAll(), HttpStatus.OK);
        } if ( rating != null){
            return new ResponseEntity<>(courseRepository.findByStarRating(rating), HttpStatus.OK);
        }
        return new ResponseEntity<>(courseRepository.findByBookingsCustomerName(customerName), HttpStatus.OK);

    }
}
