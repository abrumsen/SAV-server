package be.masimart.sav.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Column;
import jakarta.persistence.Table;

import java.sql.Date;

@Entity
@Table(name = "comment")
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_comment")
    private Long reviewId;

    @Column(name = "id_product")
    private int productId;

    @Column(name = "username")
    private String username;

    @Column(name = "comment", columnDefinition = "TEXT")
    private String comment;

    @Column(name = "rating", columnDefinition = "TINYINT UNSIGNED")
    private Byte rating;

    @Column(name = "timestamp")
    private Date timestamp;

    // Constructors
    public Comment() {}
    public Comment(int productId, String username, String comment, Byte rating, Date timestamp) {
        this.productId = productId;
        this.username = username;
        this.comment = comment;
        this.rating = rating;
        this.timestamp = timestamp;
    }

    // Getters & setters
    public Long getReviewId() {
        return reviewId;
    }
    public void setReviewId(Long reviewId) {
        this.reviewId = reviewId;
    }

    public int getProductId() {
        return productId;
    }
    public void setProductId(int productId) {
        this.productId = productId;
    }

    public String getUsername() {
        return username;
    }
    public void setUsername(String username) {
        this.username = username;
    }

    public String getComment() {
        return comment;
    }
    public void setComment(String comment) {
        this.comment = comment;
    }

    public Byte getRating() {
        return rating;
    }
    public void setRating(Byte rating) {
        this.rating = rating;
    }

    public Date getTimestamp() {
        return timestamp;
    }
    public void setTimestamp(Date timestamp) {
        this.timestamp = timestamp;
    }
}
