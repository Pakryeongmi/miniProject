package io.mini.jejoTrip.domain.reviews;

import static javax.persistence.FetchType.LAZY;

import io.mini.jejoTrip.domain.reviews.dto.CommentsDTO;
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
public class Comments {

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long id;


        @Column
        private String content;

        @ManyToOne(fetch = LAZY)
        @JoinColumn(name = "reviews_id", referencedColumnName = "id")
        private Reviews reviews;
        private String nickName;
    //    @ManyToOne(fetch = LAZY)
    //    @JoinColumn(name = "users", referencedColumnName = "id")
    //    private Users users;

//        public static Comments ofCreate(String content, Reviews reviews) {
//            Comments instance = new Comments();
//            instance.content = content;
//            instance.reviews = reviews;
//    //        instance.nickName = nickName;
//            return instance;
//        }

        public CommentsDTO convertToCommentsDTO(){
            return CommentsDTO.builder()
                .id(this.id)
                .content(this.content)
                .reviewsId(this.reviews.getId())
                .nickName(nickName).build();
        }

        public static Comments of(String nickName, String content){
                Comments comments = new Comments();
                comments.nickName = nickName;
                comments.content = content;
                return comments;
        }

        public void appendReviews(Reviews reviews){
                this.reviews = reviews;
        }


//        public void update(String content){
//            this.content = content;
//        }

    }

