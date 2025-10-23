package com.example.shoppinglist.service;

import com.example.shoppinglist.domain.ShopUser;
import com.example.shoppinglist.domain.ShopUserRepository;
import org.springframework.security.core.userdetails.User.UserBuilder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

import static org.springframework.security.core.userdetails.User.withUsername;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {
    private final ShopUserRepository shopUserRepository;

    public UserDetailsServiceImpl(ShopUserRepository shopUserRepository) {
        this.shopUserRepository = shopUserRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<ShopUser> user = shopUserRepository.findByUsername(username);

        UserBuilder builder = null;
        if(user.isPresent()){
            ShopUser currentUser = user.get();
            builder = withUsername(username);
            builder.password(currentUser.getPassword()).roles(currentUser.getRole());
        } else {
            throw new UsernameNotFoundException("User not Found");
        }

        return builder.build();
    }
}
