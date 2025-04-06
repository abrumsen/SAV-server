package be.masimart.sav.repository;

import be.masimart.sav.model.Cause;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CauseRepository extends JpaRepository<Cause, Integer> {
    Optional<Cause> findByCause(String cause);
}