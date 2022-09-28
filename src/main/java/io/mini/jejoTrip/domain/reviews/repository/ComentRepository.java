package io.mini.jejoTrip.domain.reviews.repository;

import io.mini.jejoTrip.domain.reviews.Comments;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ComentRepository extends JpaRepository<Comments, Long> {
    List<Comments> findAll();
    List<Comments> findByReviewsId(Long reviewId);

}
