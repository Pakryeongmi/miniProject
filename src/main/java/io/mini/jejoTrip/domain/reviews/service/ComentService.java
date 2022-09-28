package io.mini.jejoTrip.domain.reviews.service;

import io.mini.jejoTrip.domain.reviews.Comments;
import io.mini.jejoTrip.domain.reviews.Reviews;
import io.mini.jejoTrip.domain.reviews.controller.v1.Request.CommentsCreateRequest;
import io.mini.jejoTrip.domain.reviews.controller.v1.Request.CommentsModifyRequest;
import io.mini.jejoTrip.domain.reviews.dto.CommentsDTO;
import io.mini.jejoTrip.domain.reviews.dto.ReviewsDTO;
import io.mini.jejoTrip.domain.reviews.repository.ComentRepository;
import io.mini.jejoTrip.domain.reviews.repository.ReviewRepository;
import io.mini.jejoTrip.domain.users.Users;
import io.mini.jejoTrip.domain.users.repository.UserRepository;

import java.util.ArrayList;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
@Log4j2
public class ComentService {
    private final ComentRepository comentRepository;

    private final ReviewRepository reviewRepository;
    public void deleteComent(Long id) {

        comentRepository.deleteById(id);

    }

//    public CommentsDTO updateComments(Long id) {
//
////        Comments comments = comentRepository.findById(id)
////            .orElseThrow(() -> new RuntimeException("존재하지 않는 댓글입니다."));
//
//        comments.update(request.getContent());
//        return comments.convertToCommentsDTO();
//    }



    public List<CommentsDTO> getComments(Long reviewId) {

        List<Comments> comments = comentRepository.findByReviewsId(reviewId);
        // dto로 변환 후 return
        List<CommentsDTO> commentsDTOList = comments.stream().map(Comments::convertToCommentsDTO)
            .collect(Collectors.toList());

        return commentsDTOList;


    }

    public void create(CommentsDTO commentsDTO){

        Reviews reviews = reviewRepository.findById(commentsDTO.getReviewsId())
            .orElseThrow(() -> new RuntimeException("리뷰가 존재하지 않습니다."));
//        Users users = userRepository.findById(request.getUsersId())
//            .orElseThrow(() -> new RuntimeException("존재하지 않는 유저입니다."));

//        Comments comments = Comments.ofCreate(request.getContent());
        log.info(commentsDTO);
        Comments comments = Comments.of(commentsDTO.getNickName(), commentsDTO.getContent());
        comments.appendReviews(reviews);
        comentRepository.save(comments);}
//    }
//
//    private static final int BLOCK_PAGE_NUM_COUNT = 5; // 블럭에 존재하는 페이지 번호 수
//    private static final int PAGE_POST_COUNT = 6; // 한 페이지에 존재하는 게시글 수
//
//    @Transactional
//    public List<CommentsDTO> getBoardlist(Integer pageNum) {
//        Page<Comments> page = comentRepository.findAll(PageRequest.of(
//                pageNum - 1, PAGE_POST_COUNT));
//        log.info("msg",page);
//        List<Comments> commentsEntities = page.getContent();
//        log.info("msg",commentsEntities);
//        List<CommentsDTO> commentsDTOList = new ArrayList<>();
//
//        for (Comments comments : commentsEntities) {
//            commentsDTOList.add(comments.convertToCommentsDTO());
//        }
//        log.info("msg",commentsDTOList);
//        return commentsDTOList;
//    }
//    @Transactional
//    public Long getBoardCount() {
//        return reviewRepository.count();
//    }
//
//    public Integer[] getPageList(Integer curPageNum) {
//        Integer[] pageList = new Integer[BLOCK_PAGE_NUM_COUNT];
//
//        // 총 게시글 갯수
//        Double postsTotalCount = Double.valueOf(this.getBoardCount());
//
//        // 총 게시글 기준으로 계산한 마지막 페이지 번호 계산 (올림으로 계산)
//        Integer totalLastPageNum = (int)(Math.ceil((postsTotalCount/PAGE_POST_COUNT)));
//
//        // 현재 페이지를 기준으로 블럭의 마지막 페이지 번호 계산
//        Integer blockLastPageNum = (totalLastPageNum > curPageNum + BLOCK_PAGE_NUM_COUNT)
//                ? curPageNum + BLOCK_PAGE_NUM_COUNT
//                : totalLastPageNum;
//
//        // 페이지 시작 번호 조정
//        curPageNum = (curPageNum <= 5) ? 1 : curPageNum - 2;
//
//        // 페이지 번호 할당
//        for (int val = curPageNum, idx = 0; val <= blockLastPageNum; val++, idx++) {
//            compageList[idx] = val;
//        }
//
//        return compageList;
//    }

}
