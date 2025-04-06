package be.masimart.sav.service;

import be.masimart.sav.model.Comment;
import be.masimart.sav.repository.CommentRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import java.util.List;
import java.util.Optional;
import java.time.LocalDateTime;

@Service
public class CommentService {
    private final CommentRepository commentRepository;

    public CommentService(CommentRepository commentRepository) {
        this.commentRepository = commentRepository;
    }

    // Get all comments for a product
    public List<Comment> getCommentsByProductId(Integer productId) {
        return commentRepository.findByProductId(productId);
    }

    // Add a new comment
    @Transactional
    public Comment addComment(Integer productId, String username, String comment, Integer rating) {
        Comment newComment = new Comment(productId, username, comment, rating, LocalDateTime.now());
        return commentRepository.save(newComment);
    }

    // Update a comment
    @Transactional
    public Optional<Comment> updateComment(Long reviewId, Integer rating, String comment) {
        return commentRepository.findById(reviewId).map(existingComment -> {
            existingComment.setRating(rating);
            existingComment.setComment(comment);
            existingComment.setTimestamp(LocalDateTime.now());
            return commentRepository.save(existingComment);
        });
    }

    // Delete a comment
    @Transactional
    public Optional<Long> deleteComment(Long reviewId) {
        return commentRepository.findById(reviewId).map(comment -> {
            commentRepository.delete(comment);
            return reviewId;
        });
    }
}
