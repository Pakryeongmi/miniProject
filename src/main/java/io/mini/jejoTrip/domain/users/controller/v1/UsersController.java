package io.mini.jejoTrip.domain.users.controller.v1;

import io.mini.jejoTrip.domain.users.dto.LoginDTO;
import io.mini.jejoTrip.domain.users.dto.UsersDTO;
import io.mini.jejoTrip.domain.users.service.UsersService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;

@Controller
@AllArgsConstructor
@RequestMapping("/")
public class UsersController {
    private UsersService usersService;


    // 회원가입 페이지
    @GetMapping("/user/signup")
    public String dispSignup() {
        return "/signup";
    }


    // 회원가입 처리
    @PostMapping("/user/signup")
    public String execSignup(UsersDTO memberDto) {
        usersService.joinUser(memberDto);

        return "redirect:/user/login";
    }

    // 로그인 페이지
    @GetMapping("/user/login")
    public String dispLogin() {
        return "/login";
    }


    // 로그아웃 결과 페이지
    @GetMapping("/user/logout/result")
    public String dispLogout() {
        return "/logout";
    }

    // 내 정보 페이지
    @GetMapping("/user/info")
    public String dispMyInfo() {
        return "/myinfo";
    }



}