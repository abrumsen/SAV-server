package be.masimart.sav.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Column;
import jakarta.persistence.Table;

import java.sql.Date;

@Entity
@Table(name = "warranty")
public class Warranty {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int warrantyId;

    @Column(name = "num_order")
    private Long orderId;

    @Column(name = "id_product")
    private int productId;

    @Column(name = "end_date")
    private Date warrantyEndDate;

    // Constructors
    public Warranty() {}
    public Warranty(int warrantyId, Long orderId, int productId, Date warrantyEndDate) {
        this.warrantyId = warrantyId;
        this.orderId = orderId;
        this.productId = productId;
        this.warrantyEndDate = warrantyEndDate;
    }

    // Getters & setters
    public int getWarrantyId() {
        return warrantyId;
    }
    public void setWarrantyId(int warrantyId) {
        this.warrantyId = warrantyId;
    }

    public Long getOrderId() {
        return orderId;
    }
    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }

    public int getProductId() {
        return productId;
    }
    public void setProductId(int productId) {
        this.productId = productId;
    }

    public Date getWarrantyEndDate() {
        return warrantyEndDate;
    }
    public void setWarrantyEndDate(Date warrantyEndDate) {
        this.warrantyEndDate = warrantyEndDate;
    }
}
