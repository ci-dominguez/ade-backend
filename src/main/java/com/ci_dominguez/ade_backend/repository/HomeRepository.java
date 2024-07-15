package com.ci_dominguez.ade_backend.repository;

import com.ci_dominguez.ade_backend.model.Home;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.math.BigDecimal;
import java.util.List;


public interface HomeRepository extends JpaRepository<Home, Long> {
    /////////////////////Repository Methods/////////////////////
    /**
     * Retrieves a paginated list of Home entities with optional filtering and sorting
     *
     * @param filter Optional string to filter homes by address
     * @param sortBy Optional string to specify the sorting field
     * @param pageable Pageable object for pagination
     * @return A Page of Home entities matching the given criteria
     */
    @Query("SELECT h FROM Home h WHERE (:filter IS NULL OR h.address LIKE %:filter%) " +
            "ORDER BY CASE " +
            "WHEN :sortBy = 'cost' THEN h.cost " +
            "WHEN :sortBy = 'bedrooms' THEN h.bedrooms " +
            "WHEN :sortBy = 'bathrooms' THEN h.bathrooms " +
            "ELSE h.id END")
    Page<Home> findHomesWithFilterAndSort(@Param("filter") String filter,
                                          @Param("sortBy") String sortBy,
                                          Pageable pageable);

    /**
     * Retrieves a list of Home entities within a specified cost range
     *
     * @param minCost The minimum cost of homes to retrieve
     * @param maxCost The maximum cost of homes to retrieve
     * @return A List of Home entities within the specified cost range
     */
    @Query("SELECT h FROM Home h WHERE h.cost BETWEEN :minCost AND :maxCost")
    List<Home> findHomesByCostRange(@Param("minCost") BigDecimal minCost,
                                    @Param("maxCost") BigDecimal maxCost);

    /**
     * Retrieves a list of Home entities with at least the specified number of bedrooms and bathrooms
     *
     * @param minBedrooms The minimum number of bedrooms
     * @param minBathrooms The minimum number of bathrooms
     * @return A List of Home entities meeting the bedroom and bathroom criteria
     */
    @Query("SELECT h FROM Home h WHERE h.bedrooms >= :minBedrooms AND h.bathrooms >= :minBathrooms")
    List<Home> findHomesByMinBedroomsAndBathrooms(@Param("minBedrooms") BigDecimal minBedrooms,
                                                  @Param("minBathrooms") BigDecimal minBathrooms);

    /**
     * Retrieves a list of the most recently added Home entities
     *
     * @param pageable Pageable object to limit the number of results
     * @return A List of the most recently added Home entities
     */
    @Query("SELECT h FROM Home h ORDER BY h.createdAt DESC")
    List<Home> findRecentlyAddedHomes(Pageable pageable);

}
