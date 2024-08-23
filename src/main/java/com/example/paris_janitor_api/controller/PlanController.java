package com.example.paris_janitor_api.controller;

import com.example.paris_janitor_api.model.Plan;
import com.example.paris_janitor_api.service.PlanService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Log4j2
@RestController
@RequestMapping("/api/plan")
public class PlanController {

    @Autowired
    private PlanService planService;


    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Optional<Plan>> getPlanById(@PathVariable String id) {
        return ResponseEntity.status(HttpStatus.OK).body(planService.findById(id));
    }

    @PostMapping(value="/",consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Plan> save(@RequestBody Plan plan) {
        return  ResponseEntity.status(HttpStatus.OK).body(planService.save(plan));
    }

    @GetMapping(value = "/",produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Iterable<Plan>> getPlans() {
        return ResponseEntity.status(HttpStatus.OK).body(planService.findAll());
    }

    @DeleteMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public  ResponseEntity<Void> deletePlan(@RequestParam String id) {

        try{
            planService.deleteById(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }catch (Exception e){
            return new ResponseEntity<>(HttpStatus.UNPROCESSABLE_ENTITY);

        }
    }
}
