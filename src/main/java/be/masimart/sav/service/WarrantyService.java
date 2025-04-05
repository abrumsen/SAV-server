package be.masimart.sav.service;

import be.masimart.sav.model.Warranty;
import be.masimart.sav.repository.WarrantyRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.Optional;

@Service
public class WarrantyService {
    private final WarrantyRepository warrantyRepository;

    public WarrantyService(WarrantyRepository warrantyRepository) {
        this.warrantyRepository = warrantyRepository;
    }

    // Récupérer les garanties par ID de commande
    public List<Warranty> getWarrantiesByOrderId(Long orderId) {
        return warrantyRepository.findByOrderId(orderId);
    }

    // Enregistrer une nouvelle garantie
    @Transactional
    public Warranty registerWarranty(Long orderId, Integer productId, Integer warrantyPeriodMonths) {
        // Calculer la date de fin de garantie
        LocalDateTime warrantyEndDate = LocalDateTime.now().plusMonths(warrantyPeriodMonths);

        // Vérifier si une garantie existe déjà pour cette commande et ce produit
        Optional<Warranty> existingWarranty = warrantyRepository.findByOrderIdAndProductId(orderId, productId);

        if (existingWarranty.isPresent()) {
            // Mettre à jour la garantie existante
            Warranty warranty = existingWarranty.get();
            warranty.setWarrantyEndDate(warrantyEndDate);
            return warrantyRepository.save(warranty);
        } else {
            // Créer une nouvelle garantie
            Warranty warranty = new Warranty();
            warranty.setOrderId(orderId);
            warranty.setProductId(productId);
            warranty.setWarrantyEndDate(warrantyEndDate);
            return warrantyRepository.save(warranty);
        }
    }

    // Prolonger une garantie existante
    @Transactional
    public Optional<Warranty> extendWarranty(Long orderId, Integer productId, Integer warrantyPeriodMonths) {
        return warrantyRepository.findByOrderIdAndProductId(orderId, productId)
                .map(warranty -> {
                    // Obtenir la date actuelle de fin de garantie et ajouter des mois
                    LocalDateTime currentEndDate = warranty.getWarrantyEndDate();
                    LocalDateTime newEndDate;

                    if (currentEndDate != null) {
                        newEndDate = currentEndDate.plusMonths(warrantyPeriodMonths);
                    } else {
                        newEndDate = LocalDateTime.now().plusMonths(warrantyPeriodMonths);
                    }

                    warranty.setWarrantyEndDate(newEndDate);
                    return warrantyRepository.save(warranty);
                });
    }
}