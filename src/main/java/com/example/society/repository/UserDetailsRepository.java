package com.example.society.repository;

import com.example.society.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserDetailsRepository extends JpaRepository<User,String> {
}
