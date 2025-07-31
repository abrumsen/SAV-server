package be.masimart.sav.controller;

import be.masimart.sav.model.Warranty;
import be.masimart.sav.service.WarrantyService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/warranty")
public class WarrantyController {
    public static final String PRODUCT_ID = "productId";
    public static final String WARRANTY_END_DATE = "warrantyEndDate";
    private final WarrantyService warrantyService;

    public WarrantyController(WarrantyService warrantyService) {
        this.warrantyService = warrantyService;
    }


    @GetMapping("/{orderId}")
    public ResponseEntity<?> getWarrantyInfo(@PathVariable Long orderId) {
        List<Warranty> warranties = warrantyService.getWarrantiesByOrderId(orderId);

        if (warranties.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        List<Map<String, Object>> warrantyInfoList = new ArrayList<>();
        for (Warranty warranty : warranties) {
            Map<String, Object> warrantyInfo = new HashMap<>();
            warrantyInfo.put(PRODUCT_ID, warranty.getProductId());
            warrantyInfo.put(WARRANTY_END_DATE, warranty.getWarrantyEndDate());
            warrantyInfoList.add(warrantyInfo);
        }

        Map<String, Object> response = new HashMap<>();
        response.put("warranties", warrantyInfoList);

        return ResponseEntity.ok(response);
    }

    // POST /warranty
    @PostMapping
    public ResponseEntity<?> registerWarranty(@RequestBody Map<String, Object> payload) {
        Long orderId = Long.valueOf(payload.get("orderId").toString());
        Integer productId = Integer.parseInt(payload.get(PRODUCT_ID).toString());
        Integer warrantyPeriodMonths = Integer.parseInt(payload.get("warrantyPeriodMonths").toString());

        Warranty registeredWarranty = warrantyService.registerWarranty(orderId, productId, warrantyPeriodMonths);

        Map<String, Object> response = new HashMap<>();
        response.put(WARRANTY_END_DATE, registeredWarranty.getWarrantyEndDate());

        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @PutMapping("/{orderId}")
    public ResponseEntity<?> extendWarranty(
            @PathVariable Long orderId,
            @RequestBody Map<String, Object> payload) {

        Integer productId = Integer.parseInt(payload.get(PRODUCT_ID).toString());
        Integer warrantyPeriodMonths = Integer.parseInt(payload.get("warrantyPeriodMonths").toString());

        return warrantyService.extendWarranty(orderId, productId, warrantyPeriodMonths)
                .map(warranty -> {
                    Map<String, Object> response = new HashMap<>();
                    response.put("orderId", warranty.getOrderId());
                    response.put(PRODUCT_ID, warranty.getProductId());
                    response.put(WARRANTY_END_DATE, warranty.getWarrantyEndDate());

                    return ResponseEntity.ok(response);
                })
                .orElse(ResponseEntity.notFound().build());
    }
}
