package com.in28minutes.rest.webservices.restful_web_services.user;

import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

@Component
public class UserDAOService {
    private static List<User> users = new ArrayList<>();
    private static int usersCount=0;
    static{
        users.add(new User(++usersCount,"Adam", LocalDate.now().minusYears(30)));
        users.add(new User(++usersCount,"Eve", LocalDate.now().minusYears(25)));
        users.add(new User(++usersCount,"John", LocalDate.now().minusYears(35)));

    }
    public List<User> findAll()
    {
        return users;
    }

    public User findById(int id)
    {
        Predicate<? super User> predicate = user -> user.getId().equals(id);
        return users.stream().filter(predicate).findFirst().orElse(null);
    }
    public void deleteById(int id)
    {
        Predicate<? super User> predicate = user -> user.getId().equals(id);
        users.removeIf(predicate);
        //return users.stream().filter(predicate).findFirst().orElse(null);
    }

    public User save(User user)
    {   user.setId(++usersCount);
        users.add(user);
        return user;
    }
}
