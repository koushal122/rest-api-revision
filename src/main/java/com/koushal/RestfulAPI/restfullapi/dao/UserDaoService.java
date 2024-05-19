package com.koushal.RestfulAPI.restfullapi.dao;

import com.koushal.RestfulAPI.restfullapi.data.User;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;


@Service
public class UserDaoService {
    List<User> users;
    int cnt=0;

    public UserDaoService(){
        users=new ArrayList<>();
        users.add(new User(++cnt,"Koushal Jha", LocalDate.now().minusYears(20)));
        users.add(new User(++cnt,"Radhe Jha", LocalDate.now().minusYears(23)));
        users.add(new User(++cnt,"Ram yadav", LocalDate.now().minusYears(24)));
    }

    public List<User> findAll(){
        return users;
    }

    public User findById(int id){
        Predicate<? super User> predicate=user -> user.getId()==id;
        return users.stream().filter(predicate).findFirst().orElse(null);
    }

    public void removeById(int id){
        Predicate<? super User> predicate=user -> user.getId()==id;
        users.removeIf(predicate);
    }

    public User addUser(User user){
        user.setId(++cnt);
        users.add(user);
        return user;
    }
}
