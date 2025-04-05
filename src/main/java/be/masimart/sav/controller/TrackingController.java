package be.masimart.sav.controller;

import be.masimart.sav.service.TrackingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.Map;
import java.util.List;

@RestController
@RequestMapping("/tracking")
public class TrackingController {

    @Autowired
    private TrackingService trackingService;

    @GetMapping("/{orderId}")
    public Map<String, Object> getTracking(@PathVariable Long orderId) {
        return trackingService.getTrackingInfo(orderId);
    }

    @PostMapping
    public Map<String, Long> createTracking(@RequestBody Map<String, Object> request) {
        Map<String, String> address = (Map<String, String>) request.get("deliveryAddress");
        LocalDateTime date = LocalDateTime.parse((String) request.get("deliveryDate"));
        Long driverId = Long.valueOf(request.get("driverId").toString());
        String comment = (String) request.get("comment");

        var tracking = trackingService.createTracking(address.get("street"), address.get("number"),
                address.get("postalCode"), address.get("city"), date, driverId, comment);

        return Map.of("orderId", tracking.getOrderId());
    }

    @GetMapping("/deliveries/{driverId}")
    public List<Map<String, Object>> getDriverDeliveries(@PathVariable Long driverId) {
        return trackingService.getDeliveriesByDriver(driverId);
    }

    @PutMapping("/{orderId}/status")
    public Map<String, Object> updateStatus(@PathVariable Long orderId, @RequestBody Map<String, String> request) {
        return trackingService.updateStatus(orderId, request.get("status"), request.get("location"));
    }

    @PutMapping("/{orderId}/driver")
    public Map<String, Object> updateDriver(@PathVariable Long orderId, @RequestBody Map<String, Long> request) {
        return trackingService.updateDriver(orderId, request.get("driverId"));
    }

    @PutMapping("/{orderId}/date")
    public Map<String, Object> updateDate(@PathVariable Long orderId, @RequestBody Map<String, Object> request) {
        LocalDateTime date = LocalDateTime.parse((String) request.get("estimatedDelivery"));
        boolean isClient = (Boolean) request.get("isClient");
        return trackingService.updateDate(orderId, date, isClient);
    }

    @PutMapping("/{orderId}/address")
    public Map<String, Object> updateAddress(@PathVariable Long orderId, @RequestBody Map<String, Map<String, String>> request) {
        Map<String, String> address = request.get("deliveryAddress");
        return trackingService.updateAddress(orderId,
                address.get("street"),
                address.get("number"),
                address.get("postalCode"),
                address.get("city"));
    }

    @PutMapping("/{orderId}/comment")
    public Map<String, Object> updateComment(@PathVariable Long orderId, @RequestBody Map<String, Object> request) {
        String comment = (String) request.get("comment");
        return trackingService.updateComment(orderId, comment);
    }
}
