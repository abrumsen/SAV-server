package be.masimart.sav.controller;

import be.masimart.sav.model.Favorite;
import be.masimart.sav.service.FavoriteService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/favorites")
public class FavoriteController {
    private final FavoriteService favoriteService;

    public FavoriteController(FavoriteService favoriteService) {
        this.favoriteService = favoriteService;
    }

    // GET /favorites/all/{userId} → Get user's favorites
    @GetMapping("/all/{userId}")
    public ResponseEntity<List<Favorite>> getFavoritesByUserId(@PathVariable Integer userId) {
        List<Favorite> favorites = favoriteService.getFavoritesByUserId(userId);
        return ResponseEntity.ok(favorites);
    }

    // POST /favorites → Add a favorite
    @PostMapping
    public ResponseEntity<Map<String, Long>> addFavorite(@RequestBody Map<String, Integer> request) {
        Integer userId = request.get("userId");
        Integer productId = request.get("productId");
        Favorite favorite = favoriteService.addFavorite(userId, productId);
        return ResponseEntity.ok(Map.of("favoriteId", favorite.getFavoriteId()));
    }

    // DELETE /favorites/{favoriteId} → Remove a favorite
    @DeleteMapping("/{favoriteId}")
    public ResponseEntity<Map<String, Long>> deleteFavorite(@PathVariable Long favoriteId) {
        Optional<Long> deletedId = favoriteService.deleteFavorite(favoriteId);
        return deletedId.map(id -> ResponseEntity.ok(Map.of("favoriteId", id)))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }
}