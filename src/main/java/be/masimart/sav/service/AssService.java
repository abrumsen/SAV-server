package be.masimart.sav.service;

import be.masimart.sav.model.Ass;
import be.masimart.sav.model.Cause;
import be.masimart.sav.model.StatusRemboursement;
import be.masimart.sav.repository.AssRepository;
import be.masimart.sav.repository.CauseRepository;
import be.masimart.sav.repository.StatusRemboursementRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class AssService {

    private final AssRepository assRepository;
    private final CauseRepository causeRepository;
    private final StatusRemboursementRepository statusRemboursementRepository;

    @Autowired
    public AssService(
            AssRepository assRepository,
            CauseRepository causeRepository,
            StatusRemboursementRepository statusRemboursementRepository) {
        this.assRepository = assRepository;
        this.causeRepository = causeRepository;
        this.statusRemboursementRepository = statusRemboursementRepository;
    }

    public Ass getClaimById(Long claimId) {
        return assRepository.findById(claimId)
                .orElseThrow(() -> new RuntimeException("Claim not found with id: " + claimId));
    }

    public List<Ass> getClaimsByClientId(Integer clientId) {
        return assRepository.findByClientId(clientId);
    }

    @Transactional
    public Ass createClaim(Ass newClaim) {
        // Validate that cause exists
        Cause cause = causeRepository.findById(newClaim.getCauseId())
                .orElseThrow(() -> new RuntimeException("Cause not found with id: " + newClaim.getCauseId()));

        // Default status (assuming 1 is for "New" or initial status)
        StatusRemboursement defaultStatus = statusRemboursementRepository.findById(1)
                .orElseThrow(() -> new RuntimeException("Default status not found"));

        // Set additional fields
        newClaim.setRemboursementStatusId(defaultStatus.getRemboursementStatusId());
        newClaim.setTimestamp(LocalDateTime.now());
        newClaim.setCause(cause);
        newClaim.setStatusRemboursement(defaultStatus);

        return assRepository.save(newClaim);
    }

    @Transactional
    public Ass updateClaimStatus(Long claimId, String statusName) {
        Ass existingClaim = assRepository.findById(claimId)
                .orElseThrow(() -> new RuntimeException("Claim not found with id: " + claimId));

        StatusRemboursement newStatus = statusRemboursementRepository.findByStatus(statusName)
                .orElseThrow(() -> new RuntimeException("Status not found: " + statusName));

        existingClaim.setRemboursementStatusId(newStatus.getRemboursementStatusId());
        existingClaim.setStatusRemboursement(newStatus);

        return assRepository.save(existingClaim);
    }

    @Transactional
    public Map<String, Long> deleteClaim(Long claimId) {
        if (!assRepository.existsById(claimId)) {
            throw new RuntimeException("Claim not found with id: " + claimId);
        }

        assRepository.deleteById(claimId);

        Map<String, Long> response = new HashMap<>();
        response.put("claimId", claimId);
        return response;
    }
}