package com.koushal.RestfulAPI.restfullapi.Repository;

import com.koushal.RestfulAPI.restfullapi.data.Post;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostRepository extends JpaRepository<Post,Integer> {
}
