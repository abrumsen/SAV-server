package be.masimart.sav.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Column;
import jakarta.persistence.Table;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.JoinColumn;
import jakarta.validation.constraints.Negative;

import java.time.LocalDateTime;

@Entity
@Table(name = "tracking_state")
public class TrackingState {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "num_order", nullable = false)
    private Long orderId;

    @Column(name = "timestamp", nullable = false)
    private LocalDateTime timestamp;

    @Column(name = "id_action", nullable = false)
    private Integer actionId;

    @Column(name = "location", nullable = false, length = 2)
    private String location;

    @ManyToOne
    @JoinColumn(name = "num_order", insertable = false, updatable = false, nullable = false)
    private Tracking tracking;

    @ManyToOne
    @JoinColumn(name = "id_action", insertable = false, updatable = false, nullable = false)
    private Action action;

    // Constructors
    public TrackingState() {}
    public TrackingState(Long orderId, LocalDateTime timestamp, Integer actionId, String location, Tracking tracking, Action action) {
        this.orderId = orderId;
        this.timestamp = timestamp;
        this.actionId = actionId;
        this.location = location;
        this.tracking = tracking;
        this.action = action;
    }

    // Getters & setters
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }

    public Long getOrderId() {
        return orderId;
    }
    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }
    public void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }

    public Integer getActionId() {
        return actionId;
    }
    public void setActionId(Integer actionId) {
        this.actionId = actionId;
    }

    public String getLocation() {
        return location;
    }
    public void setLocation(String location) {
        this.location = location;
    }

    public Tracking getTracking() {
        return tracking;
    }
    public void setTracking(Tracking tracking) {
        this.tracking = tracking;
    }

    public Action getAction() {
        return action;
    }
    public void setAction(Action action) {
        this.action = action;
    }
}
