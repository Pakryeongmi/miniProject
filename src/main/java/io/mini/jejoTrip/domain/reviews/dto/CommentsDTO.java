package io.mini.jejoTrip.domain.reviews.dto;

import io.mini.jejoTrip.domain.reviews.Comments;
import io.mini.jejoTrip.domain.reviews.Reviews;
import io.mini.jejoTrip.domain.users.dto.UsersDTO;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter
@Setter
@Builder
public class CommentsDTO {
    private Long id;
    private Long reviewsId;
    private String nickName;
    private String content;


    public Comments convertToNewEntity(Reviews reviews) {
        return Comments.builder()
                .content(content)
                .nickName(nickName)
                .reviews(reviews)
                .build();
    }
}