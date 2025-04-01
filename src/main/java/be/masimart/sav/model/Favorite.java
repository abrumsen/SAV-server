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
    private int userId;

    @Column(name = "id_product")
    private int productId;

    // Constructors
    public Favorite() {}
    public Favorite(int userId, int productId) {
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

    public int getUserId() {
        return userId;
    }
    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getProductId() {
        return productId;
    }
    public void setProductId(int productId) {
        this.productId = productId;
    }
}
