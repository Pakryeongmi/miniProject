package io.mini.jejoTrip.domain.reviews.dto;

import io.mini.jejoTrip.domain.reviews.Reviews;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;

@Data
@Getter
@Setter
@Builder
public class ReviewsDTO {
    private Long id;
    private String revTitles;
    private String tags;
    private String contents;
    private String nickNames;
    private String places;


    public Reviews toEntity() {
        Reviews reviews = Reviews.builder()
                .id(id)
                .revTitles(revTitles)
                .tags(tags)
                .contents(contents)
                .places(places)
                .nickNames(nickNames)
                .build();
        return reviews;
    }
}
