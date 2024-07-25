package com.ci_dominguez.ade_backend.repository;

import com.ci_dominguez.ade_backend.model.Visits;
import com.ci_dominguez.ade_backend.model.enums.VisitStatus;
import com.ci_dominguez.ade_backend.model.enums.VisitType;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface VisitsRepository extends JpaRepository<Visits, Long>{
    /////////////////////Repository Methods/////////////////////
    /**
     * More methods to be added / Documentation to be added
     *
     */

    List<Visits> findByStatus(VisitStatus status);

    List<Visits> findByType(VisitType type);

    List<Visits> findByVisitorNameContainingIgnoreCase(String visitorName);
}
