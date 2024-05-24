package com.koushal.RestfulAPI.restfullapi.controllers;


import com.koushal.RestfulAPI.restfullapi.Repository.PostRepository;
import com.koushal.RestfulAPI.restfullapi.Repository.UserRepository;
import com.koushal.RestfulAPI.restfullapi.dao.UserDaoService;
import com.koushal.RestfulAPI.restfullapi.data.Post;
import com.koushal.RestfulAPI.restfullapi.data.User;
import com.koushal.RestfulAPI.restfullapi.exceptions.UserNotFoundException;
import org.aspectj.bridge.Message;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.Locale;

@RestController
public class UserController {
    UserRepository userRepository;
    MessageSource messageSource;
    PostRepository postRepository;

    public UserController(UserRepository userRepository,MessageSource messageSource,PostRepository postRepository ){
        this.userRepository=userRepository;
        this.messageSource=messageSource;
        this.postRepository=postRepository;
    }

    @GetMapping(path = "/users")
    public List<User> getAllUsers(){
        return userRepository.findAll();
    }

    @GetMapping(path = "/users/{id}")
    public EntityModel<User> getUserById(@PathVariable int id){
        User user=userRepository.findById(id);
        if(user==null) throw new UserNotFoundException("User not found with id = "+id);
        EntityModel<User> entityModel=EntityModel.of(user);
        WebMvcLinkBuilder linkBuilder=WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(this.getClass()).getAllUsers());
        entityModel.add(linkBuilder.withRel("all-users"));
        return entityModel;
    }

    @DeleteMapping(path = "/users/{id}")
    public void deleteUserById(@PathVariable int id){
        userRepository.deleteById(id);
    }

    @PostMapping(path = "/users")
    //Here user should be sent in request body and it should be same as User class
    public ResponseEntity<User> saveUser(@RequestBody User user){
        User createdUser=userRepository.save(user);
        //In place of hard coding URL here we are getting current POST request URL
        //and adding id to it.
        URI location= ServletUriComponentsBuilder.fromCurrentRequest()
                                                 .path("/{id}")
                                                 .buildAndExpand(createdUser.getId())
                                                 .toUri();
        //we can pass location like on which URL it was created.
        return ResponseEntity.created(location).build();
    }

    @GetMapping(path = "/users/{id}/posts")
    public List<Post> getPostsByUserId(@PathVariable int id){
        User user=userRepository.findById(id);
        if(user==null) throw new UserNotFoundException("User not found with id = "+id);
        return user.getPosts();
    }

    @GetMapping(path = "/good-morning-internalization")
    public String goodMorningMessageInternationalization(){
        Locale locale= LocaleContextHolder.getLocale();
        return messageSource.getMessage("good.morning.message",null,"default message",locale);
    }

    @PostMapping(path = "/users/{id}/posts")
    public void setPostForUser(@RequestBody Post post,@PathVariable int id){
        User user=userRepository.findById(id);
        post.setUser(user);
        postRepository.save(post);
    }
}
