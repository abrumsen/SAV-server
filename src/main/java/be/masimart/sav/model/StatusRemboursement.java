package be.masimart.sav.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Column;
import jakarta.persistence.Table;

@Entity
@Table(name = "status_remboursement")
public class StatusRemboursement {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_remboursement_status")
    private Integer remboursementStatusId;

    @Column(name = "status", nullable = false)
    private String status;

    @Column(name = "description")
    private String description;

    // Constructors
    public StatusRemboursement() {}
    public StatusRemboursement(String status, String description) {
        this.status = status;
        this.description = description;
    }

    // Getters & setters
    public Integer getRemboursementStatusId() {
        return remboursementStatusId;
    }
    public void setRemboursementStatusId(Integer remboursementStatusId) {
        this.remboursementStatusId = remboursementStatusId;
    }

    public String getStatus() {
        return status;
    }
    public void setStatus(String status) {
        this.status = status;
    }

    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }
}
