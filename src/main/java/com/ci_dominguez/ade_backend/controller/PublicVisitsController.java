package com.ci_dominguez.ade_backend.controller;

import com.ci_dominguez.ade_backend.model.Visits;
import com.ci_dominguez.ade_backend.service.VisitsService;
import jakarta.validation.ConstraintViolationException;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/public/visit-requests")
public class PublicVisitsController {
    private static final Logger logger = LoggerFactory.getLogger(PublicVisitsController.class);

    private final VisitsService visitsService;

    public PublicVisitsController(VisitsService visitsService){
        this.visitsService = visitsService;
    }

    @PostMapping
    public ResponseEntity<?> submitVisitRequest(@Valid @RequestBody Visits visitReq){
        try{
            logger.info("Received visit request submission");
            Visits savedReq = visitsService.saveVisit(visitReq);
            logger.info("Visit request saved successfully with ID: {}", savedReq.getId());
            return ResponseEntity.ok(savedReq);
        } catch (ConstraintViolationException e){
            logger.error("Validation error: {}", e.getMessage());
            return ResponseEntity.badRequest().body("Invalid input: "+ e.getMessage());
        } catch (DataAccessException e){
            logger.error("Database error: {}", e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Database error has occurred");
        } catch (Exception e){
            logger.error("Unexpected error: {}", e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("An unexpected error occurred");
        }
    }
}
