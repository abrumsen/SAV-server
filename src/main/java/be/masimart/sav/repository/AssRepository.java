package be.masimart.sav.repository;

import be.masimart.sav.model.Ass;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AssRepository extends JpaRepository<Ass, Long> {
    List<Ass> findByClientId(Integer clientId);
}