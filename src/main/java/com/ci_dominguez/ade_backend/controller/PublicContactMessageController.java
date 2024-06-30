package com.ci_dominguez.ade_backend.controller;

import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.dao.DataAccessException;
import jakarta.validation.ConstraintViolationException;

import com.ci_dominguez.ade_backend.model.ContactMessage;
import com.ci_dominguez.ade_backend.service.ContactMessageService;

@RestController
@RequestMapping("/api/public/contact-messages")
public class PublicContactMessageController {

    private static final Logger logger = LoggerFactory.getLogger(PublicContactMessageController.class);
    private final ContactMessageService contactMessageService;

    public PublicContactMessageController(ContactMessageService contactMessageService) {
        this.contactMessageService = contactMessageService;
    }

    @PostMapping
    public ResponseEntity<?> submitContactMessage(@Valid @RequestBody ContactMessage contactMessage) {
        try {
            logger.info("Received contact message submission");
            ContactMessage savedMessage = contactMessageService.saveContactMessage(contactMessage);
            logger.info("Contact message saved successfully with ID: {}", savedMessage.getId());
            return ResponseEntity.ok(savedMessage);
        } catch (ConstraintViolationException e) {
            logger.error("Validation error: {}", e.getMessage());
            return ResponseEntity.badRequest().body("Invalid input: " + e.getMessage());
        } catch (DataAccessException e) {
            logger.error("Database error: {}", e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Database error occurred");
        } catch (Exception e) {
            logger.error("Unexpected error: {}", e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("An unexpected error occurred");
        }
    }
}
