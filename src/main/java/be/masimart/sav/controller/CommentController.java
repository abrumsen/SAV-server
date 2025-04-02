package be.masimart.sav.controller;

import be.masimart.sav.model.Comment;
import be.masimart.sav.service.CommentService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/reviews")
public class CommentController {
    private final CommentService commentService;

    public CommentController(CommentService commentService) {
        this.commentService = commentService;
    }

    // GET /reviews/{productId} → Get comments for a product
    @GetMapping("/{productId}")
    public ResponseEntity<List<Comment>> getCommentsByProductId(@PathVariable int productId) {
        List<Comment> comments = commentService.getCommentsByProductId(productId);
        return ResponseEntity.ok(comments);
    }

    // POST /reviews → Add a comment
    @PostMapping
    public ResponseEntity<Map<String, Long>> addComment(@RequestBody Map<String, Object> request) {
        int productId = (int) request.get("productId");
        String username = (String) request.get("username");
        String comment = (String) request.get("comment");
        Byte rating = ((Number) request.get("rating")).byteValue();

        Comment newComment = commentService.addComment(productId, username, comment, rating);
        return ResponseEntity.ok(Map.of("reviewId", newComment.getReviewId()));
    }

    // PUT /reviews/{reviewId} → Update a comment
    @PutMapping("/{reviewId}")
    public ResponseEntity<Comment> updateComment(@PathVariable Long reviewId, @RequestBody Map<String, Object> request) {
        Byte rating = ((Number) request.get("rating")).byteValue();
        String comment = (String) request.get("comment");

        return commentService.updateComment(reviewId, rating, comment)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    // DELETE /reviews/{reviewId} → Delete a comment
    @DeleteMapping("/{reviewId}")
    public ResponseEntity<Map<String, Long>> deleteComment(@PathVariable Long reviewId) {
        Optional<Long> deletedId = commentService.deleteComment(reviewId);
        return deletedId.map(id -> ResponseEntity.ok(Map.of("reviewId", id)))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }
}
