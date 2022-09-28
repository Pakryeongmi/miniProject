package io.mini.jejoTrip.domain.users;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Entity
public class Users {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String userName; //사용자 e-mail

    @Column
    private String passWord;

    @Column
    private String realName;

    @Column
    private String nickName;

    @Column
    private String sex;

    @Column
    private Integer age;


}