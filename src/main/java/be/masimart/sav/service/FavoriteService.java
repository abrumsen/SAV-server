package be.masimart.sav.service;

import be.masimart.sav.model.Favorite;
import be.masimart.sav.repository.FavoriteRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.Optional;

@Service
public class FavoriteService {
    private final FavoriteRepository favoriteRepository;

    public FavoriteService(FavoriteRepository favoriteRepository) {
        this.favoriteRepository = favoriteRepository;
    }

    public List<Favorite> getFavoritesByUserId(int userId) {
        return favoriteRepository.findByUserId(userId);
    }

    @Transactional
    public Favorite addFavorite(int userId, int productId) {
        Favorite favorite = new Favorite(userId, productId);
        return favoriteRepository.save(favorite);
    }

    @Transactional
    public Optional<Long> deleteFavorite(Long favoriteId) {
        Optional<Favorite> favorite = favoriteRepository.findById(favoriteId);
        favorite.ifPresent(favoriteRepository::delete);
        return favorite.map(Favorite::getFavoriteId);
    }
}