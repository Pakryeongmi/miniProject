package io.mini.jejoTrip.domain.reviews.service;

import io.mini.jejoTrip.domain.reviews.Reviews;
import io.mini.jejoTrip.domain.reviews.dto.ReviewsDTO;
import io.mini.jejoTrip.domain.reviews.repository.ReviewRepository;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

@Service
@AllArgsConstructor
@Transactional
public class ReviewService  {
    private ReviewRepository reviewRepository;
    private static final int BLOCK_PAGE_NUM_COUNT = 5; // 블럭에 존재하는 페이지 번호 수
    private static final int PAGE_POST_COUNT = 6; // 한 페이지에 존재하는 게시글 수


//    public Long regiReview(ReviewsDTO reviewsDTO) {
//
//        return reviewRepository.save(reviewsDTO.toEntity()).getId();
//    }
//
//   @Transactional(readOnly = true)
//    public List<Reviews> searchArticleByPlace(String place){
//        List<Reviews> result = reviewRepository.findByPlaces(place);
//        return result;
//   }
//
//    @Transactional(readOnly = true)
//    public List<Reviews> searchArticleByTag(String tags){
//        List<Reviews> result = reviewRepository.findByTags(tags);
//        return result;
//    }
//
//    @Transactional(readOnly = true)
//    public List<Reviews> searchArticleByNickNames(String nickNames){
//        List<Reviews> result = reviewRepository.findByNickNames(nickNames);
//        return result;
//    }
    private ReviewsDTO convertEntityToDto(Reviews reviews) {
        return ReviewsDTO.builder()
                .id(reviews.getId())
                .revTitles(reviews.getRevTitles())
                .tags(reviews.getTags())
                .contents(reviews.getContents())
                .places(reviews.getPlaces())
                .nickNames(reviews.getNickNames())
                .build();
    }



    @Transactional
    public List<ReviewsDTO> getBoardlist(Integer pageNum) {
        Page<Reviews> page = reviewRepository.findAll(PageRequest.of(
                pageNum - 1, PAGE_POST_COUNT));

        List<Reviews> reviewsEntities = page.getContent();
        List<ReviewsDTO> reviewsDTOList = new ArrayList<>();

        for (Reviews reviews : reviewsEntities) {
            reviewsDTOList.add(this.convertEntityToDto(reviews));
        }

        return reviewsDTOList;
    }

    @Transactional
    public ReviewsDTO getPost(Long id) {
        // Optional : NPE(NullPointerException) 방지
        Optional<Reviews> boardWrapper = reviewRepository.findById(id);
        Reviews reviews = boardWrapper.get();

        ReviewsDTO reviewsDTO =  ReviewsDTO.builder()
                .id(reviews.getId())
                .revTitles(reviews.getRevTitles())
                .tags(reviews.getTags())
                .contents(reviews.getContents())
                .places(reviews.getPlaces())
                .nickNames(reviews.getNickNames())
                .build();

        return reviewsDTO;
    }

    @Transactional
    public void savePost(ReviewsDTO reviewsDTO){

       reviewRepository.save(reviewsDTO.toEntity()).getId();

    }

    @Transactional
    public void deletePost(Long id) {
        reviewRepository.deleteById(id);
    }

    // 검색 API
    @Transactional
    public List<ReviewsDTO> searchPostsBytags(String tags) {
        List<Reviews> reviewsEntities = reviewRepository.findByTags(tags);
        List<ReviewsDTO> reviewsDTOList = new ArrayList<>();

        if (reviewsEntities.isEmpty()) return reviewsDTOList;

        for (Reviews reviews : reviewsEntities) {
            reviewsDTOList.add(this.convertEntityToDto(reviews));
        }

        return reviewsDTOList;
    }

    @Transactional
    public List<ReviewsDTO> searchPostsBynickNames(String nickNames) {
        List<Reviews> reviewsEntities = reviewRepository.findByTags(nickNames);
        List<ReviewsDTO> reviewsDTOList = new ArrayList<>();

        if (reviewsEntities.isEmpty()) return reviewsDTOList;

        for (Reviews reviews : reviewsEntities) {
            reviewsDTOList.add(this.convertEntityToDto(reviews));
        }

        return reviewsDTOList;
    }

    @Transactional
    public List<ReviewsDTO> searchPostsByplaces(String places) {
        List<Reviews> reviewsEntities = reviewRepository.findByPlaces(places);
        List<ReviewsDTO> reviewsDTOList = new ArrayList<>();

        if (reviewsEntities.isEmpty()) return reviewsDTOList;

        for (Reviews reviews : reviewsEntities) {
            reviewsDTOList.add(this.convertEntityToDto(reviews));
        }

        return reviewsDTOList;
    }


    // 페이징
    @Transactional
    public Long getBoardCount() {
        return reviewRepository.count();
    }

    public Integer[] getPageList(Integer curPageNum) {
        Integer[] pageList = new Integer[BLOCK_PAGE_NUM_COUNT];

        // 총 게시글 갯수
        Double postsTotalCount = Double.valueOf(this.getBoardCount());

        // 총 게시글 기준으로 계산한 마지막 페이지 번호 계산 (올림으로 계산)
        Integer totalLastPageNum = (int)(Math.ceil((postsTotalCount/PAGE_POST_COUNT)));

        // 현재 페이지를 기준으로 블럭의 마지막 페이지 번호 계산
        Integer blockLastPageNum = (totalLastPageNum > curPageNum + BLOCK_PAGE_NUM_COUNT)
                ? curPageNum + BLOCK_PAGE_NUM_COUNT
                : totalLastPageNum;

        // 페이지 시작 번호 조정
        curPageNum = (curPageNum <= 5) ? 1 : curPageNum - 2;

        // 페이지 번호 할당
        for (int val = curPageNum, idx = 0; val <= blockLastPageNum; val++, idx++) {
            pageList[idx] = val;
        }

        return pageList;
    }
}