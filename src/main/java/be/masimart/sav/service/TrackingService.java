package be.masimart.sav.service;

import be.masimart.sav.model.*;
import be.masimart.sav.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.*;

@Service
public class TrackingService {

    @Autowired
    private TrackingRepository trackingRepo;

    @Autowired
    private TrackingStateRepository stateRepo;

    @Autowired
    private ActionRepository actionRepo;

    public Tracking createTracking(String street, String number, String postalCode, String city, LocalDateTime deliveryDate, Long driverId, String comment) {
        Tracking tracking = new Tracking(street, number, postalCode, city, comment, 0, driverId, deliveryDate);
        trackingRepo.save(tracking);

        Action created = actionRepo.findByAction("CREATED").orElseThrow();
        TrackingState state = new TrackingState(tracking.getOrderId(), LocalDateTime.now(), created.getActionId(), "WH", tracking, created);
        stateRepo.save(state);

        return tracking;
    }

    public Map<String, Object> getTrackingInfo(Long orderId) {
        Tracking tracking = trackingRepo.findById(orderId).orElseThrow();
        List<TrackingState> history = stateRepo.findByOrderIdOrderByTimestampAsc(orderId);
        TrackingState currentState = stateRepo.findTopByOrderIdOrderByTimestampDesc(orderId);

        Map<String, Object> response = new LinkedHashMap<>();
        response.put("orderId", tracking.getOrderId());
        response.put("driverId", tracking.getDriverId());
        response.put("deliveryAddress", Map.of(
                "street", tracking.getDeliveryAddressStreet(),
                "number", tracking.getDeliveryAddressNum(),
                "postalCode", tracking.getDeliveryAddressPc(),
                "city", tracking.getDeliveryAddressCity()
        ));
        response.put("comment", tracking.getComment());
        response.put("changes", tracking.getChanges());
        response.put("status", currentState.getAction().getAction());
        response.put("location", currentState.getLocation());
        response.put("timestamp", currentState.getTimestamp());
        response.put("deliveryDate", tracking.getDeliveryDate());
        response.put("history", history.stream().map(state ->
                Map.of("timestamp", state.getTimestamp(),
                        "event", state.getAction().getAction(),
                        "location", state.getLocation())
        ).toList());

        return response;
    }

    public List<Map<String, Object>> getDeliveriesByDriver(Long driverId) {
        List<Tracking> list = trackingRepo.findByDriverId(driverId);
        List<Map<String, Object>> result = new ArrayList<>();

        for (Tracking t : list) {
            TrackingState state = stateRepo.findTopByOrderIdOrderByTimestampDesc(t.getOrderId());
            result.add(Map.of(
                    "orderId", t.getOrderId(),
                    "deliveryAddress", Map.of(
                            "street", t.getDeliveryAddressStreet(),
                            "number", t.getDeliveryAddressNum(),
                            "postalCode", t.getDeliveryAddressPc(),
                            "city", t.getDeliveryAddressCity()
                    ),
                    "status", state.getAction().getAction(),
                    "location", state.getLocation(),
                    "timestamp", state.getTimestamp(),
                    "deliveryDate", t.getDeliveryDate()
            ));
        }
        return result;
    }

    public Map<String, Object> updateStatus(Long orderId, String status, String location) {
        Tracking tracking = trackingRepo.findById(orderId).orElseThrow();
        Action action = actionRepo.findByAction(status).orElseThrow();
        TrackingState state = new TrackingState(orderId, LocalDateTime.now(), action.getActionId(), location, tracking, action);
        stateRepo.save(state);
        return getTrackingInfo(orderId);
    }

    public Map<String, Object> updateDriver(Long orderId, Long driverId) {
        Tracking tracking = trackingRepo.findById(orderId).orElseThrow();
        tracking.setDriverId(driverId);
        trackingRepo.save(tracking);
        return getTrackingInfo(orderId);
    }

    public Map<String, Object> updateDate(Long orderId, LocalDateTime newDate, boolean isClient) {
        Tracking tracking = trackingRepo.findById(orderId).orElseThrow();
        tracking.setDeliveryDate(newDate);
        if (isClient) {
            tracking.setChanges(tracking.getChanges() + 1);
        }
        trackingRepo.save(tracking);
        return getTrackingInfo(orderId);
    }

    public Map<String, Object> updateAddress(Long orderId, String street, String number, String postalCode, String city) {
        Tracking tracking = trackingRepo.findById(orderId).orElseThrow();
        tracking.setDeliveryAddressStreet(street);
        tracking.setDeliveryAddressNum(number);
        tracking.setDeliveryAddressPc(postalCode);
        tracking.setDeliveryAddressCity(city);
        tracking.setChanges(tracking.getChanges() + 1);
        trackingRepo.save(tracking);
        return getTrackingInfo(orderId);
    }

    public Map<String, Object> updateComment(Long orderId, String comment) {
        Tracking tracking = trackingRepo.findById(orderId).orElseThrow();
        tracking.setComment(comment);
        tracking.setChanges(tracking.getChanges() + 1);
        trackingRepo.save(tracking);
        return getTrackingInfo(orderId);
    }
}
