package com.koushal.RestfulAPI.restfullapi.Repository;

import com.koushal.RestfulAPI.restfullapi.data.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User,Integer> {
    User findById(int id);
}
