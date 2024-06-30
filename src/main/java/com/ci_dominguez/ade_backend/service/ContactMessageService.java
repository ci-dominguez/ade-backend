package com.ci_dominguez.ade_backend.service;

import com.ci_dominguez.ade_backend.exception.DatabaseException;
import com.ci_dominguez.ade_backend.model.ContactMessage;
import com.ci_dominguez.ade_backend.model.enums.MessageStatus;
import com.ci_dominguez.ade_backend.repository.ContactMessageRepository;
import jakarta.validation.ValidationException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataAccessException;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class ContactMessageService {
    private static final Logger logger = LoggerFactory.getLogger(ContactMessageService.class);

    //Inject ContactMessageRepository
    private final ContactMessageRepository contactMessageRepository;

    public ContactMessageService(ContactMessageRepository contactMessageRepository){
        this.contactMessageRepository = contactMessageRepository;
    }

    /////////////////////Service Methods/////////////////////
    /**
     * Saves a new ContactMessage after performing
     * the following validation checks:
     * - Email format using a regex pattern
     * - Phone number format (must be 10-15 digits)
     * - Presence of all required fields (name, message, email, phone number)
     *
     * @param contactMessage The ContactMessage object to be saved
     * @return The saved ContactMessage object
     * @throws ValidationException if any validation check fails
     */
    @Transactional
    public ContactMessage saveContactMessage(ContactMessage contactMessage){
        try {
            //Validate email format
            if (!contactMessage.getEmail().matches("^[A-Za-z0-9+_.-]+@(.+)$")){
                throw new ValidationException("Invalid email format");
            }
            //Validate phone number format
            if (!contactMessage.getPhoneNumber().matches("^\\d{3}-\\d{3}-\\d{4}$")) {
                throw new ValidationException("Invalid phone number format");
            }
            //Ensure all required fields are present
            if (contactMessage.getName().isEmpty() || contactMessage.getMessage().isEmpty()
                    || contactMessage.getEmail().isEmpty() || contactMessage.getPhoneNumber().isEmpty()) {
                throw new ValidationException("All fields are required");
            }
            //Save and log the message
            ContactMessage savedMessage = contactMessageRepository.save(contactMessage);
            logger.info("Contact message saved successfully: {}", savedMessage.getId());
            return savedMessage;
        } catch (ValidationException e) {
            logger.error("Validation error: {}", e.getMessage());
            throw e;
        } catch (DataAccessException e) {
            logger.error("Database error: {}", e.getMessage());
            throw new DatabaseException("Failed to save contact message", e);
        }
    }

    /**
     * Retrieves all ContactMessage entities from the database
     *
     * @return A List of all ContactMessage entities
     */
    public List<ContactMessage> getAllContactMessages(){
        return contactMessageRepository.findAll();
    }

    /**
     * Retrieves ContactMessage entities with a specific status
     *
     * @param status The MessageStatus to filter by
     * @return A List of ContactMessage entities matching the given status
     */
    public  List<ContactMessage> getContactMessageByStatus(MessageStatus status){
        return contactMessageRepository.findByStatus(status);
    }

    /**
     * Counts the number of ContactMessage entities with a specific status
     *
     * @param status The MessageStatus to count
     * @return The number of ContactMessage entities with the given status
     */
    public Integer countContactMessagesByStatus(MessageStatus status){
        return contactMessageRepository.countByStatus(status);
    }

    /**
     * Retrieves ContactMessage entities created within a specified date range
     *
     * @param start The start date and time of the range
     * @param end The end date and time of the range
     * @return A List of ContactMessage entities created within the specified date range
     */
    public List<ContactMessage> getContactMessagesByDateRange(LocalDateTime start, LocalDateTime end){
        return contactMessageRepository.findByCreatedAtBetween(start, end);
    }

    /**
     * Searches for ContactMessage entities by name, ignoring case
     *
     * @param name The name to search for
     * @return A List of ContactMessage entities with names containing the specified string
     */
    public  List<ContactMessage> searchContactMessagesByName(String name){
        return contactMessageRepository.findByNameContainingIgnoreCase(name);
    }

    /**
     * Retrieves all ContactMessage entities sorted by a specified field and order
     *
     * @param sortBy The field to sort by
     * @param order The sort order ("asc" for ascending, "desc" for descending)
     * @return A List of all ContactMessage entities, sorted as specified
     */
    public List<ContactMessage> getSortedContactMessages(String sortBy, String order){
        Sort sort = Sort.by(Sort.Order.by(sortBy).with(Sort.Direction.fromString(order)));
        return contactMessageRepository.findAll(sort);
    }
}
