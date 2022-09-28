package io.mini.jejoTrip.domain.Menu;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
@AllArgsConstructor
@RequestMapping("/menu")
public class MenuController {
    @GetMapping("/woodo")
    public String goWoodo(){
        return "/woodo";
    }

    @GetMapping("/seongsanbong")
    public String goSeongsanbong() {
        return "/seongsanbong";
    }

    @GetMapping("/hanla")
    public String goHanla() {
        return "/hanla";
    }

    @GetMapping("/hyupjae")
    public String goHyupjae() {
        return "/hyupjae";
    }

    @GetMapping("/chunji")
    public String goChunji() {
        return "/chunji";
    }

    @GetMapping("/osulok")
    public String goOsulok() {
        return "/osulok";
    }
}