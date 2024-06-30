package com.ci_dominguez.ade_backend.repository;

import com.ci_dominguez.ade_backend.model.ContactMessage;
import com.ci_dominguez.ade_backend.model.enums.MessageStatus;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;

public interface ContactMessageRepository extends JpaRepository<ContactMessage, Long> {
    /////////////////////Repository Methods/////////////////////
    /**
     * Retrieves a list of ContactMessage entities with the specified status
     *
     * @param status The MessageStatus to filter by
     * @return A List of ContactMessage entities matching the given status
     */
    List<ContactMessage> findByStatus(MessageStatus status);

    /**
     * Counts the number of ContactMessage entities with the specified status
     *
     * @param status The MessageStatus to count
     * @return The number of ContactMessage entities with the given status
     */
    Integer countByStatus(MessageStatus status);

    /**
     * Finds ContactMessage entities created between the specified start and end dates
     *
     * @param start The start date and time
     * @param end The end date and time
     * @return A List of ContactMessage entities created within the specified date range
     */
    List<ContactMessage> findByCreatedAtBetween(LocalDateTime start, LocalDateTime end);

    /**
     * Searches for ContactMessage entities where the name contains the specified string, ignoring case
     *
     * @param name The name substring to search for
     * @return A List of ContactMessage entities with names containing the specified string
     */
    List<ContactMessage> findByNameContainingIgnoreCase(String name);
}
