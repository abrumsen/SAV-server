package be.masimart.sav.repository;

import be.masimart.sav.model.StatusRemboursement;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface StatusRemboursementRepository extends JpaRepository<StatusRemboursement, Integer> {
    Optional<StatusRemboursement> findByStatus(String status);
}