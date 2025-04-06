package be.masimart.sav.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Column;
import jakarta.persistence.Table;

@Entity
@Table(name = "action")
public class Action {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_action")
    private Integer actionId;

    @Column(name = "action", nullable = false)
    private String action;

    @Column(name = "description")
    private String description;

    // Constructors
    public Action() {}
    public Action(String action, String description) {
        this.action = action;
        this.description = description;
    }

    // Getters & setters
    public Integer getActionId() {
        return actionId;
    }
    public void setActionId(Integer actionId) {
        this.actionId = actionId;
    }

    public String getAction() {
        return action;
    }
    public void setAction(String action) {
        this.action = action;
    }

    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }
}
