package io.mini.jejoTrip.domain.reviews.controller.v1;

import io.mini.jejoTrip.domain.reviews.dto.CommentsDTO;
import io.mini.jejoTrip.domain.reviews.dto.ReviewsDTO;
import io.mini.jejoTrip.domain.reviews.service.ComentService;
import io.mini.jejoTrip.domain.reviews.service.ReviewService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.List;


@Controller
@AllArgsConstructor
@RequestMapping("/review")    // /board 경로로 들어오는 경우 아래의 Method들로 분기될 수 있도록 설정
public class ReviewController {
    private ReviewService reviewService;
    private ComentService comentService;
    // 게시판

    // 게시글 목록
    // list 경로로 GET 메서드 요청이 들어올 경우 list 메서드와 맵핑시킴
    // list 경로에 요청 파라미터가 있을 경우 (?page=1), 그에 따른 페이지네이션을 수행함.

    @GetMapping("/list")
    public String list(Model model, @RequestParam(value = "page", defaultValue = "1") Integer pageNum) {
        List<ReviewsDTO> reviewsList = reviewService.getBoardlist(pageNum);
        Integer[] pageList = reviewService.getPageList(pageNum);
        model.addAttribute("reviewsList", reviewsList);
        model.addAttribute("revpageList", pageList);

//        Integer[] compageList = comentService.getPageList(pageNum);
//        List<CommentsDTO> commentsList = comentService.getBoardlist(pageNum);
//        model.addAttribute("commentList", commentsList);
//        model.addAttribute("compageList", compageList);



        return "review/list";
    }

    // 글쓰는 페이지

    @GetMapping("/post")
    public String write() {
        return "review/write";
    }


    // 글을 쓴 뒤 POST 메서드로 글 쓴 내용을 DB에 저장
    // 그 후에는 /list 경로로 리디렉션해준다.

    @PostMapping("/post")
    public String write(ReviewsDTO reviewsDTO){

        reviewService.savePost(reviewsDTO);
//        model.addAttribute("message", "글 작성이 완료 되었습니다.");
//        model.addAttribute("serchUrl", "/board/list");
        return "redirect:/review/list";
    }



    // 게시물 상세 페이지이며, {no}로 페이지 넘버를 받는다.
    // PathVariable 애노테이션을 통해 no를 받음

    @GetMapping("/post/{no}")
    public String detail(@PathVariable("no") Long no, Model model) {
        ReviewsDTO reviewsDTO = reviewService.getPost(no);

        model.addAttribute("reviewsDTO", reviewsDTO);
        return "review/detail";
    }

    // 게시물 수정 페이지이며, {no}로 페이지 넘버를 받는다.

    @GetMapping("/post/edit/{no}")
    public String edit(@PathVariable("no") Long no, Model model) {
        ReviewsDTO reviewsDTO = reviewService.getPost(no);

        model.addAttribute("reviewsDTO", reviewsDTO);
        return "review/update";
    }

    // 위는 GET 메서드이며, PUT 메서드를 이용해 게시물 수정한 부분에 대해 적용

    @PutMapping("/post/edit/{id}")
    public String update(ReviewsDTO reviewsDTO)  {

        reviewService.savePost(reviewsDTO);

        return "redirect:/review/list";
    }

    // 게시물 삭제는 deletePost 메서드를 사용하여 간단하게 삭제할 수 있다.

    @DeleteMapping("/post/{no}")
    public String delete(@PathVariable("no") Long id) {
        reviewService.deletePost(id);

        return "redirect:/review/list";
    }

    // 검색
    // keyword를 view로부터 전달 받고
    // Service로부터 받은 boardDtoList를 model의 attribute로 전달해준다.

    @GetMapping("/review/search")
    public String searchtags(@RequestParam(value = "tags") String tags, Model model) {
        List<ReviewsDTO> reviewsDTOList = reviewService.searchPostsBytags(tags);
        model.addAttribute("reviewsDTOList", reviewsDTOList);

        return "redirect: review/list";
    }

}
//    @GetMapping("/review/search")
//    public String searchplace(@RequestParam(value="places") String places, Model model) {
//        List<ReviewsDTO> reviewsDTOList = reviewService.searchPostsByplaces(places);
//        model.addAttribute("reviewsDTOList", reviewsDTOList);
//
//        return "board/list";
//    }
//
//    @GetMapping("/review/search")
//    public String searchnickNames(@RequestParam(value="nickNames") String nickNames, Model model) {
//        List<ReviewsDTO> reviewsDTOList = reviewService.searchPostsBynickNames(nickNames);
//        model.addAttribute("reviewsDTOList", reviewsDTOList);
//
//        return "review/list";
//    }

