package be.masimart.sav.repository;

import be.masimart.sav.model.Action;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ActionRepository extends JpaRepository<Action, Integer> {
    Optional<Action> findByAction(String action);
}