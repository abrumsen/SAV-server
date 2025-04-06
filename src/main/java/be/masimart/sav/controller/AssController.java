package be.masimart.sav.controller;

import be.masimart.sav.model.Ass;
import be.masimart.sav.service.AssService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/ass")
public class AssController {

    private final AssService assService;

    @Autowired
    public AssController(AssService assService) {
        this.assService = assService;
    }

    @GetMapping("/{claimId}")
    public ResponseEntity<Ass> getClaimById(@PathVariable Long claimId) {
        Ass claim = assService.getClaimById(claimId);
        return ResponseEntity.ok(claim);
    }

    @GetMapping("/client/{clientId}")
    public ResponseEntity<List<Ass>> getClaimsByClientId(@PathVariable Integer clientId) {
        List<Ass> claims = assService.getClaimsByClientId(clientId);
        return ResponseEntity.ok(claims);
    }

    @PostMapping
    public ResponseEntity<Map<String, Long>> createClaim(@RequestBody Ass newClaim) {
        Ass createdClaim = assService.createClaim(newClaim);
        Map<String, Long> response = Collections.singletonMap("claimId", createdClaim.getId());
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @PutMapping("/{claimId}")
    public ResponseEntity<Ass> updateClaimStatus(
            @PathVariable Long claimId,
            @RequestBody Map<String, String> updateData) {

        String statusName = updateData.get("status");
        Ass updatedClaim = assService.updateClaimStatus(claimId, statusName);
        return ResponseEntity.ok(updatedClaim);
    }

    @DeleteMapping("/{claimId}")
    public ResponseEntity<Map<String, Long>> deleteClaim(@PathVariable Long claimId) {
        assService.deleteClaim(claimId);
        Map<String, Long> response = Collections.singletonMap("claimId", claimId);
        return ResponseEntity.ok(response);
    }
}
