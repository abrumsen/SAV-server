package be.masimart.sav.service;

import be.masimart.sav.model.Comment;
import be.masimart.sav.repository.CommentRepository;
import org.springframework.stereotype.Service;


import java.util.List;
import java.util.Optional;

@Service
public class CommentService {
    private final CommentRepository commentRepository;

    public CommentService(CommentRepository commentRepository) {
        this.commentRepository = commentRepository;
    }

    // Get all comments for a product
    public List<Comment> getCommentsByProductId(int productId) {
        return commentRepository.findByProductId(productId);
    }

    // Add a new comment
    public Comment addComment(int productId, String username, String comment, Byte rating) {
        Comment newComment = new Comment(productId, username, comment, rating, new java.sql.Date(System.currentTimeMillis()));
        return commentRepository.save(newComment);
    }

    // Update a comment
    public Optional<Comment> updateComment(Long reviewId, Byte rating, String comment) {
        return commentRepository.findById(reviewId).map(existingComment -> {
            existingComment.setRating(rating);
            existingComment.setComment(comment);
            existingComment.setTimestamp(new java.sql.Date(System.currentTimeMillis()));  // Corrected
            return commentRepository.save(existingComment);
        });
    }

    // Delete a comment
    public Optional<Long> deleteComment(Long reviewId) {
        return commentRepository.findById(reviewId).map(comment -> {
            commentRepository.delete(comment);
            return reviewId;
        });
    }
}
