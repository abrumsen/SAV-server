package be.masimart.sav.service;

import be.masimart.sav.model.Warranty;
import be.masimart.sav.repository.WarrantyRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Date;
import java.util.Calendar;
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
    public Warranty registerWarranty(Long orderId, int productId, int warrantyPeriodMonths) {
        // Calculer la date de fin de garantie
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.MONTH, warrantyPeriodMonths);
        Date warrantyEndDate = new Date(calendar.getTimeInMillis());

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
    public Optional<Warranty> extendWarranty(Long orderId, int productId, int warrantyPeriodMonths) {
        return warrantyRepository.findByOrderIdAndProductId(orderId, productId)
                .map(warranty -> {
                    // Obtenir la date actuelle de fin de garantie
                    Calendar calendar = Calendar.getInstance();
                    if (warranty.getWarrantyEndDate() != null) {
                        calendar.setTime(warranty.getWarrantyEndDate());
                    }

                    // Ajouter la période d'extension
                    calendar.add(Calendar.MONTH, warrantyPeriodMonths);
                    Date newEndDate = new Date(calendar.getTimeInMillis());

                    warranty.setWarrantyEndDate(newEndDate);
                    return warrantyRepository.save(warranty);
                });
    }
}