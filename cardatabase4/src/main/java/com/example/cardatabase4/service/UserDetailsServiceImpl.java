package com.example.cardatabase4.service;

import com.example.cardatabase4.domain.AppUser;
import com.example.cardatabase4.domain.AppUserRepository;
import org.springframework.security.core.userdetails.User.UserBuilder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

import static org.springframework.security.core.userdetails.User.withUsername;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {
    private final AppUserRepository appUserRepository;

    public UserDetailsServiceImpl(AppUserRepository appUserRepository) {
        this.appUserRepository = appUserRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException { // 강제로 오버라이딩 해야함
        Optional<AppUser> user = appUserRepository.findByUsername(username); // 결과값이 unique라서 Optional로 자료형을 지정함.
                                                                            // 직접 메서드를 생성함 -> AppRepository에서 자동 생성해줌.
        UserBuilder builder = null;
        if(user.isPresent()){
            AppUser currentUser = user.get(); // user 자체는 Optional 자료형이지 AppUser가 아니기 때문에 get으로 꺼내야한다.
            builder = withUsername(username);
            builder.password(currentUser.getPassword()).roles((currentUser.getRole()));
        } else {
            throw new UsernameNotFoundException("User Not Found.");
        }

        return builder.build();
    }
}
