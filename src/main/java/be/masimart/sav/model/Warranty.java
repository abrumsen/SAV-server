package be.masimart.sav.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Column;
import jakarta.persistence.Table;

import java.time.LocalDateTime;

@Entity
@Table(name = "warranty")
public class Warranty {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer warrantyId;

    @Column(name = "num_order")
    private Long orderId;

    @Column(name = "id_product")
    private Integer productId;

    @Column(name = "end_date")
    private LocalDateTime warrantyEndDate;

    // Constructors
    public Warranty() {}
    public Warranty(Integer warrantyId, Long orderId, Integer productId, LocalDateTime warrantyEndDate) {
        this.warrantyId = warrantyId;
        this.orderId = orderId;
        this.productId = productId;
        this.warrantyEndDate = warrantyEndDate;
    }

    // Getters & setters
    public Integer getWarrantyId() {
        return warrantyId;
    }
    public void setWarrantyId(Integer warrantyId) {
        this.warrantyId = warrantyId;
    }

    public Long getOrderId() {
        return orderId;
    }
    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }

    public Integer getProductId() {
        return productId;
    }
    public void setProductId(Integer productId) {
        this.productId = productId;
    }

    public LocalDateTime getWarrantyEndDate() {
        return warrantyEndDate;
    }
    public void setWarrantyEndDate(LocalDateTime warrantyEndDate) {
        this.warrantyEndDate = warrantyEndDate;
    }
}
