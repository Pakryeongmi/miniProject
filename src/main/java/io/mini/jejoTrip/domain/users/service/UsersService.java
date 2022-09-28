package io.mini.jejoTrip.domain.users.service;

import io.mini.jejoTrip.domain.users.dto.LoginDTO;
import io.mini.jejoTrip.domain.users.dto.UsersDTO;
import io.mini.jejoTrip.common.enums.Role;
import io.mini.jejoTrip.domain.users.Users;
import io.mini.jejoTrip.domain.users.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
@Transactional
public class UsersService implements UserDetailsService {
    private UserRepository userRepository;

    public Long joinUser(UsersDTO usersDTO) {
        // 비밀번호 암호화
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        usersDTO.setPassWord(passwordEncoder.encode(usersDTO.getPassWord()));

        return userRepository.save(usersDTO.toEntity()).getId();
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
//        Optional<Users> usersWrapper = userRepository.findByUsername(username);
//        Users users = usersWrapper.get();

        Users users = userRepository.findByUserName(username)
            .orElseThrow(() -> new RuntimeException("존재하지 않는 회원입니다."));

        List<GrantedAuthority> authorities = new ArrayList<>();

        if (("admin@example.com").equals(username)) {
            authorities.add(new SimpleGrantedAuthority(Role.ADMIN.getValue()));
        } else {
            authorities.add(new SimpleGrantedAuthority(Role.MEMBER.getValue()));
        }

        return new User(users.getUserName(), users.getPassWord(), authorities);
    }



}