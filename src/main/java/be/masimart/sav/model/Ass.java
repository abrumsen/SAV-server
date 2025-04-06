package be.masimart.sav.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Column;
import jakarta.persistence.Table;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.JoinColumn;

import java.time.LocalDateTime;

@Entity
@Table(name = "ass")
public class Ass {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "id_customer", nullable = false)
    private Integer clientId;

    @Column(name = "id_product", nullable = false)
    private Integer productId;

    @Column(name = "id_cause", nullable = false)
    private Integer causeId;

    @Column(name = "comment")
    private String comment;

    @Column(name = "id_remboursement_status", nullable = false)
    private Integer remboursementStatusId;

    @Column(name = "amount", nullable = false)
    private Integer amount;

    @Column(name = "timestamp", nullable = false)
    private LocalDateTime timestamp;

    @ManyToOne
    @JoinColumn(name = "id_cause", insertable = false, updatable = false, nullable = false)
    private Cause cause;

    @ManyToOne
    @JoinColumn(name = "id_remboursement_status", insertable = false, updatable = false, nullable = false)
    private StatusRemboursement statusRemboursement;

    // Constructors
    public Ass() {}
    public Ass(Integer clientId, Integer productId, Integer causeId, String comment, Integer remboursementStatusId, Integer amount, LocalDateTime timestamp, Cause cause, StatusRemboursement statusRemboursement) {
        this.clientId = clientId;
        this.productId = productId;
        this.causeId = causeId;
        this.comment = comment;
        this.remboursementStatusId = remboursementStatusId;
        this.amount = amount;
        this.timestamp = timestamp;
        this.cause = cause;
        this.statusRemboursement = statusRemboursement;
    }

    // Getters & setters
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }

    public Integer getClientId() {
        return clientId;
    }
    public void setClientId(Integer clientId) {
        this.clientId = clientId;
    }

    public Integer getProductId() {
        return productId;
    }
    public void setProductId(Integer productId) {
        this.productId = productId;
    }

    public Integer getCauseId() {
        return causeId;
    }
    public void setCauseId(Integer causeId) {
        this.causeId = causeId;
    }

    public String getComment() {
        return comment;
    }
    public void setComment(String comment) {
        this.comment = comment;
    }

    public Integer getRemboursementStatusId() {
        return remboursementStatusId;
    }
    public void setRemboursementStatusId(Integer remboursementStatusId) {
        this.remboursementStatusId = remboursementStatusId;
    }

    public Integer getAmount() {
        return amount;
    }
    public void setAmount(Integer amount) {
        this.amount = amount;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }
    public void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }

    public Cause getCause() {
        return cause;
    }
    public void setCause(Cause cause) {
        this.cause = cause;
    }

    public StatusRemboursement getStatusRemboursement() {
        return statusRemboursement;
    }

    public void setStatusRemboursement(StatusRemboursement statusRemboursement) {
        this.statusRemboursement = statusRemboursement;
    }
}