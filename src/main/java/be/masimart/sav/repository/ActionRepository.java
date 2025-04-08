package be.masimart.sav.repository;

import be.masimart.sav.model.Action;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ActionRepository extends JpaRepository<Action, Integer> {
    Optional<Action> findByAction(String action);
}