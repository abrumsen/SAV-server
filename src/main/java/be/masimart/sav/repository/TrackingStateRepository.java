package be.masimart.sav.repository;

import be.masimart.sav.model.TrackingState;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TrackingStateRepository extends JpaRepository<TrackingState, Long> {
    List<TrackingState> findByOrderIdOrderByTimestampAsc(Long orderId);
    TrackingState findTopByOrderIdOrderByTimestampDesc(Long orderId);
}