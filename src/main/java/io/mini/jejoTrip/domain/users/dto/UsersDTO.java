package io.mini.jejoTrip.domain.users.dto;

import io.mini.jejoTrip.domain.users.Users;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter
@Setter
public class UsersDTO {

    private Long id;

    private String userName; //사용자 e-mail

    private String passWord;

    private String realName; //실명

    private String nickName; //별명

    private String sex;

    private int age;


    /*DTO -> Entity*/
    public Users toEntity() {
        Users users = Users.builder()
                .userName(userName)
                .passWord(passWord)
                .realName(realName)
                .nickName(nickName)
                .sex(sex)
                .age(age)
                .build();
        return  users;
    }

}
