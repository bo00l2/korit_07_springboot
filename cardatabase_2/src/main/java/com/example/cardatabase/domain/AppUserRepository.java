package com.example.cardatabase.domain;

import com.example.cardatabase.AppUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.Optional;

//@RepositoryRestResource
public interface AppUserRepository extends JpaRepository<AppUser, Long> {
    Optional<AppUser> findBYUsername(String username);
}
