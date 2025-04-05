package be.masimart.sav.repository;

import be.masimart.sav.model.Tracking;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TrackingRepository extends JpaRepository<Tracking, Long> {
    List<Tracking> findByDriverId(Long driverId);
}