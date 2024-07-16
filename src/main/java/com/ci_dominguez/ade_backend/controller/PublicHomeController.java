package com.ci_dominguez.ade_backend.controller;

import com.ci_dominguez.ade_backend.dto.HomeCardDTO;
import com.ci_dominguez.ade_backend.dto.HomeDetailsDTO;
import com.ci_dominguez.ade_backend.exception.DatabaseException;
import com.ci_dominguez.ade_backend.service.HomeService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/public/homes")
public class PublicHomeController {

    private static final Logger logger = LoggerFactory.getLogger(PublicHomeController.class);

    private final HomeService homeService;

    public PublicHomeController(HomeService homeService){
        this.homeService = homeService;
    }

    ///////////////////// Endpoints /////////////////////
    /**
     * Retrieves a paginated list of homes with optional filtering and sorting
     *
     * @param filter Optional string to filter homes by address
     * @param sortBy Optional string to specify the sorting field
     * @param pageable Pageable object for pagination
     * @return A ResponseEntity containing a Page of HomeCardDTO objects matching the given criteria
     */
    @GetMapping
    public ResponseEntity<Page<HomeCardDTO>> getHomesWithFilterAndSort(
            @RequestParam(required = false) String filter,
            @RequestParam(required = false) String sortBy,
            Pageable pageable) {
        try {
            Page<HomeCardDTO> homes = homeService.getHomesWithFilterAndSort(filter, sortBy, pageable);
            return ResponseEntity.ok(homes);
        } catch (DatabaseException e) {
            logger.error("Error retrieving homes with filter and sort", e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    /**
     * Retrieves detailed information about a specific home
     *
     * @param id The ID of the home to retrieve
     * @return A ResponseEntity containing the HomeDetailsDTO of the specified home
     */
    @GetMapping("/{id}")
    public ResponseEntity<HomeDetailsDTO> getHomeDetails(@PathVariable Long id) {
        try {
            HomeDetailsDTO homeDetails = homeService.getHomeDetailsById(id);
            return ResponseEntity.ok(homeDetails);
        } catch (DatabaseException e) {
            logger.error("Error retrieving home details", e);
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

}
