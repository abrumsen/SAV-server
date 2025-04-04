package be.masimart.sav.repository;

import be.masimart.sav.model.Warranty;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface WarrantyRepository extends JpaRepository<Warranty, Integer> {
    List<Warranty> findByOrderId(Long orderId);
    Optional<Warranty> findByOrderIdAndProductId(Long orderId, int productId);
}