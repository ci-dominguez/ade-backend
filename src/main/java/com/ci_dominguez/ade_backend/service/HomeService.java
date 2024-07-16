package com.ci_dominguez.ade_backend.service;

import com.ci_dominguez.ade_backend.dto.HomeCardDTO;
import com.ci_dominguez.ade_backend.dto.HomeDetailsDTO;
import com.ci_dominguez.ade_backend.exception.DatabaseException;
import com.ci_dominguez.ade_backend.model.Home;
import com.ci_dominguez.ade_backend.model.HomeDetails;
import com.ci_dominguez.ade_backend.repository.HomeRepository;
import jakarta.validation.ValidationException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
public class HomeService {
    private static final Logger logger = LoggerFactory.getLogger(HomeService.class);

    private final HomeRepository homeRepository;

    public HomeService(HomeRepository homeRepository) {
        this.homeRepository = homeRepository;
    }

    /**
     * Retrieves a paginated list of homes with optional filtering and sorting
     *
     * @param filter Optional string to filter homes by address
     * @param sortBy Optional string to specify the sorting field
     * @param pageable Pageable object for pagination
     * @return A Page of HomeCardDTO objects matching the given criteria
     * @throws DatabaseException if there's an error accessing the database
     */
    public Page<HomeCardDTO> getHomesWithFilterAndSort(String filter, String sortBy, Pageable pageable) {
        try {
            Page<Home> homes = homeRepository.findHomesWithFilterAndSort(filter, sortBy, pageable);
            return homes.map(this::convertToHomeCardDTO);
        } catch (DataAccessException e) {
            logger.error("Error retrieving homes with filter and sort", e);
            throw new DatabaseException("Failed to retrieve homes", e);
        }
    }

    /**
     * Finds homes within a specified cost range
     *
     * @param minCost The minimum cost of homes to retrieve
     * @param maxCost The maximum cost of homes to retrieve
     * @return A List of HomeCardDTO objects within the specified cost range
     * @throws ValidationException if the cost range is invalid
     * @throws DatabaseException if there's an error accessing the database
     */
    public List<HomeCardDTO> findHomesByCostRange(BigDecimal minCost, BigDecimal maxCost) {
        if (minCost.compareTo(maxCost) > 0) {
            throw new ValidationException("Minimum cost cannot be greater than maximum cost");
        }
        try {
            List<Home> homes = homeRepository.findHomesByCostRange(minCost, maxCost);
            return homes.stream().map(this::convertToHomeCardDTO).toList();
        } catch (DataAccessException e) {
            logger.error("Error finding homes by cost range", e);
            throw new DatabaseException("Failed to find homes by cost range", e);
        }
    }

    /**
     * Finds homes with at least the specified number of bedrooms and bathrooms
     *
     * @param minBedrooms The minimum number of bedrooms
     * @param minBathrooms The minimum number of bathrooms
     * @return A List of HomeCardDTO objects meeting the bedroom and bathroom criteria
     * @throws ValidationException if the input parameters are invalid
     * @throws DatabaseException if there's an error accessing the database
     */
    public List<HomeCardDTO> findHomesByMinBedroomsAndBathrooms(BigDecimal minBedrooms, BigDecimal minBathrooms) {
        if (minBedrooms.compareTo(BigDecimal.ZERO) < 0 || minBathrooms.compareTo(BigDecimal.ZERO) < 0) {
            throw new ValidationException("Minimum bedrooms and bathrooms must be non-negative");
        }
        try {
            List<Home> homes = homeRepository.findHomesByMinBedroomsAndBathrooms(minBedrooms, minBathrooms);
            return homes.stream().map(this::convertToHomeCardDTO).toList();
        } catch (DataAccessException e) {
            logger.error("Error finding homes by minimum bedrooms and bathrooms", e);
            throw new DatabaseException("Failed to find homes by minimum bedrooms and bathrooms", e);
        }
    }

    public HomeDetailsDTO getHomeDetailsById(Long id) {
        Home home = homeRepository.findById(id)
                .orElseThrow(() -> new DatabaseException("Home not found with id: " + id));
        return convertToHomeDetailsDTO(home);
    }


    /**
     * Converts a Home entity to a HomeCardDTO
     *
     * @param home The Home entity to convert
     * @return A HomeCardDTO containing the essential information for a home card display
     */
    public HomeCardDTO convertToHomeCardDTO(Home home) {
        return new HomeCardDTO(
                home.getId(),
                home.getAddress(),
                home.getCost(),
                home.getBedrooms(),
                home.getBathrooms(),
                home.getLotSize(),
                home.getMainImgUrl()
        );
    }

    /**
     * Converts a Home entity and its associated HomeDetails to a HomeDetailsDTO
     *
     * @param home The Home entity to convert
     * @return A HomeDetailsDTO containing all the detailed information about a home
     */
    public HomeDetailsDTO convertToHomeDetailsDTO(Home home) {
        HomeDetails details = home.getHomeDetails();
        return new HomeDetailsDTO(
                home.getId(),
                home.getAddress(),
                home.getCost(),
                home.getBedrooms(),
                home.getBathrooms(),
                home.getLotSize(),
                details.getHome().getLivableAreaSize(),
                home.getMainImgUrl(),
                details.getFacts(),
                details.getWhatsSpecial(),
                details.getPhotoGallery()
        );
    }


}
