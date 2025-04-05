package be.masimart.sav.repository;

import be.masimart.sav.model.TrackingState;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TrackingStateRepository extends JpaRepository<TrackingState, Long> {
    List<TrackingState> findByOrderIdOrderByTimestampAsc(Long orderId);
    TrackingState findTopByOrderIdOrderByTimestampDesc(Long orderId);
}