package io.mini.jejoTrip.domain.reviews;

import static javax.persistence.FetchType.*;

import io.mini.jejoTrip.domain.reviews.dto.ReviewsDTO;
import io.mini.jejoTrip.domain.users.Users;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Builder
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Reviews {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String revTitles; // rev_title

    @Column
    private String tags;

    @Column
    private String contents;

    @Column
    private String places;

    @Column
    private String nickNames;

public ReviewsDTO convertToReviewsDTO() {
    return ReviewsDTO.builder()
            .id(this.id)
            .revTitles(this.revTitles)
            .tags(this.tags)
            .contents(this.contents)
            .places(this.places)
            .nickNames(this.nickNames)
            .build();
}
}