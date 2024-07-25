package com.ci_dominguez.ade_backend.service;

import com.ci_dominguez.ade_backend.exception.DatabaseException;
import com.ci_dominguez.ade_backend.model.Visits;
import com.ci_dominguez.ade_backend.repository.VisitsRepository;
import jakarta.validation.ValidationException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class VisitsService {

    private static final Logger logger = LoggerFactory.getLogger(VisitsService.class);

    //Inject ContactMessageRepository
    private final VisitsRepository visitsRepository;

    public VisitsService(VisitsRepository visitsRepository){
        this.visitsRepository = visitsRepository;
    }

    /////////////////////Service Methods/////////////////////

    /**
     * Saves a new Visit after performing
     * the following validation checks:
     * - Email format using a regex pattern
     * - Phone number format
     * - Valid future date
     * - Presence of all required fields
     *
     * @param visitReq The Visits object to be saved
     * @return the saved Visits object
     * @throws ValidationException if any validation check fails
     */
    @Transactional
    public Visits saveVisit(Visits visitReq){
        try{
            //Validate email format
            if (!visitReq.getVisitorEmail().matches("^[A-Za-z0-9+_.-]+@(.+)$")){
                throw new ValidationException("Invalid email format");
            }
            //Validate phone number format
            if (!visitReq.getVisitorPhoneNumber().matches("^\\d{3}-\\d{3}-\\d{4}$")){
                throw new ValidationException("Invalid phone number format");
            }
            //Validate requested date
            if (visitReq.getRequestedDate().isBefore(LocalDateTime.now())) {
                throw new ValidationException("Requested date must be in the future");
            }
            //Ensure all required fields are present
            if (visitReq.getVisitorName().isEmpty() || visitReq.getVisitorEmail().isEmpty()
                    || visitReq.getVisitorPhoneNumber().isEmpty() || visitReq.getVisitorComment().isEmpty()
                    || visitReq.getRequestedDate() == null){
                throw new ValidationException("All fields are required");
            }
            //Save and log the request
            Visits savedVisitReq = visitsRepository.save(visitReq);
            logger.info("Request saved successfully: {}", savedVisitReq.getId());
            return savedVisitReq;
        } catch (ValidationException e){
            logger.error("Validation error: {}", e.getMessage());
            throw e;
        } catch (DataAccessException e){
            logger.error("Database error: {}", e.getMessage());
            throw new DatabaseException("Failed to save visit request", e);
        }
    }

    /**
     * Retrieves all Visits entities from the database
     *
     * @return A List of all Visits entities
     */
    public List<Visits> getAllVisits() { return visitsRepository.findAll(); }

    /**
     * Searches for Visits entities by name, ignoring case
     *
     * @param visitorName The name to search for
     * @return A List of Visits entities with names containing the specified string
     */
    public List<Visits> searchVisitsByName(String visitorName){
        return visitsRepository.findByVisitorNameContainingIgnoreCase(visitorName);
    }
}
