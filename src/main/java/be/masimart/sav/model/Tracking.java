package be.masimart.sav.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Column;
import jakarta.persistence.Table;
import jakarta.persistence.OneToMany;

import java.time.LocalDateTime;
import java.util.List;


@Entity
@Table(name = "tracking")
public class Tracking {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "num_order")
    private Long orderId;

    @Column(name = "delivery_address_street")
    private String deliveryAddressStreet;

    @Column(name = "delivery_address_num")
    private String deliveryAddressNum;

    @Column(name = "delivery_address_pc")
    private String deliveryAddressPc;

    @Column(name = "delivery_address_city")
    private String deliveryAddressCity;

    @Column(name = "comment")
    private String comment;

    @Column(name = "number_change")
    private Integer changes;

    @Column(name = "id_driver")
    private Long driverId;

    @Column(name = "delivery_date")
    private LocalDateTime deliveryDate;

    @OneToMany(mappedBy = "tracking")
    private List<TrackingState> trackingStates;

    // Constructors
    public Tracking() {}
    public Tracking(String deliveryAddressStreet, String deliveryAddressNum, String deliveryAddressPc, String deliveryAddressCity, String comment, Integer changes, Long driverId, LocalDateTime deliveryDate) {
        this.deliveryAddressStreet = deliveryAddressStreet;
        this.deliveryAddressNum = deliveryAddressNum;
        this.deliveryAddressPc = deliveryAddressPc;
        this.deliveryAddressCity = deliveryAddressCity;
        this.comment = comment;
        this.changes = changes;
        this.driverId = driverId;
        this.deliveryDate = deliveryDate;
    }

    // Getters & setters
    public Long getOrderId() {
        return orderId;
    }
    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }

    public String getDeliveryAddressStreet() {
        return deliveryAddressStreet;
    }
    public void setDeliveryAddressStreet(String deliveryAddressStreet) {
        this.deliveryAddressStreet = deliveryAddressStreet;
    }

    public String getDeliveryAddressNum() {
        return deliveryAddressNum;
    }
    public void setDeliveryAddressNum(String deliveryAddressNum) {
        this.deliveryAddressNum = deliveryAddressNum;
    }

    public String getDeliveryAddressPc() {
        return deliveryAddressPc;
    }
    public void setDeliveryAddressPc(String deliveryAddressPc) {
        this.deliveryAddressPc = deliveryAddressPc;
    }

    public String getDeliveryAddressCity() {
        return deliveryAddressCity;
    }
    public void setDeliveryAddressCity(String deliveryAddressCity) {
        this.deliveryAddressCity = deliveryAddressCity;
    }

    public String getComment() {
        return comment;
    }
    public void setComment(String comment) {
        this.comment = comment;
    }

    public Integer getChanges() {
        return changes;
    }
    public void setChanges(Integer changes) {
        this.changes = changes;
    }

    public Long getDriverId() {
        return driverId;
    }
    public void setDriverId(Long driverId) {
        this.driverId = driverId;
    }

    public LocalDateTime getDeliveryDate() {
        return deliveryDate;
    }
    public void setDeliveryDate(LocalDateTime deliveryDate) {
        this.deliveryDate = deliveryDate;
    }
}