package io.mini.jejoTrip.domain.users.dto;

import lombok.Data;
import lombok.Getter;

@Data
@Getter
public class LoginDTO {


    private String userName;
    private String passWord; //사용자 e-mail
//    private String nickName = usersDTO.getNickName();
}
