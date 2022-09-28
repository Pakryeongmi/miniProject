package io.mini.jejoTrip.domain.reviews.controller.v1;


import io.mini.jejoTrip.domain.reviews.controller.v1.Request.CommentsCreateRequest;
import io.mini.jejoTrip.domain.reviews.controller.v1.Request.CommentsModifyRequest;
import io.mini.jejoTrip.domain.reviews.dto.CommentsDTO;
import io.mini.jejoTrip.domain.reviews.service.ComentService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import static org.springframework.http.ResponseEntity.ok;

@Controller
@RequestMapping("/comments")
@RequiredArgsConstructor
@Log4j2
public class ComentsController {

    // 필드 인젝션은 좋은 방법이 아닙니다
//    @Autowired
//    ComentService service;

    // 생성자 인젝션
    private final ComentService comentService;


//    @PostMapping("/post")
//    public ResponseEntity<?> create(CommentsDTO commentsDTO){
//        comentService.create(commentsDTO);
//        return ResponseEntity.ok(HttpStatus.CREATED);
//    }

    @PostMapping("/post")
    public String  create(

        CommentsDTO commentsDTO
    ){
        log.info(commentsDTO);
        comentService.create(commentsDTO);
    return "review/list";
    }

    @GetMapping("/show")
    public ResponseEntity<?> getComent(@RequestParam Long reviewId) {

        return ResponseEntity.ok(comentService.getComments(reviewId));
    }

    @DeleteMapping("/{commentsId}")
    public ResponseEntity<?> deleteComent(@PathVariable Long commentsId)
                              {
        comentService.deleteComent(commentsId);
        return ResponseEntity.ok(HttpStatus.OK);
    }

    @PutMapping("/{commentsId}")
    public void updateComent(@PathVariable Long commentsId, CommentsDTO commentsDTO) {

       comentService.create(commentsDTO);
    }
}
