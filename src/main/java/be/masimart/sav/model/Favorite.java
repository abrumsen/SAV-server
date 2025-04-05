package be.masimart.sav.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Column;
import jakarta.persistence.Table;

@Entity
@Table(name = "favorite")
public class Favorite {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long favoriteId;

    @Column(name = "id_customer")
    private Integer userId;

    @Column(name = "id_product")
    private Integer productId;

    // Constructors
    public Favorite() {}
    public Favorite(Integer userId, Integer productId) {
        this.userId = userId;
        this.productId = productId;
    }

    // Getters & setters
    public Long getFavoriteId() {
        return favoriteId;
    }
    public void setFavoriteId(Long favoriteId) {
        this.favoriteId = favoriteId;
    }

    public Integer getUserId() {
        return userId;
    }
    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getProductId() {
        return productId;
    }
    public void setProductId(Integer productId) {
        this.productId = productId;
    }
}
