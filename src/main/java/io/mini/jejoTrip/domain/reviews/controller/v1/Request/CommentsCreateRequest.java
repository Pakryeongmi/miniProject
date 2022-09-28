package io.mini.jejoTrip.domain.reviews.controller.v1.Request;

import lombok.Getter;

@Getter
public class CommentsCreateRequest {

    private Long usersId;
    private Long reviewsId;
    private String content;

}
