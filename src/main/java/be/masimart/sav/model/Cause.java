package be.masimart.sav.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Column;
import jakarta.persistence.Table;

@Entity
@Table(name = "cause")
public class Cause {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_cause")
    private Integer causeId;

    @Column(name = "cause", nullable = false)
    private String cause;

    @Column(name = "description")
    private String description;

    // Constructors
    public Cause() {}
    public Cause( String cause, String description) {
        this.cause = cause;
        this.description = description;
    }

    // Getters & setters
    public Integer getCauseId() {
        return causeId;
    }
    public void setCauseId(Integer causeId) {
        this.causeId = causeId;
    }

    public String getCause() {
        return cause;
    }
    public void setCause(String cause) {
        this.cause = cause;
    }

    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }
}
