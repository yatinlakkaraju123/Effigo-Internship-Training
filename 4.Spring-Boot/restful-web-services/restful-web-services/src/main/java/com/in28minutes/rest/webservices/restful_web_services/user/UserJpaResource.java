package com.in28minutes.rest.webservices.restful_web_services.user;

import com.in28minutes.rest.webservices.restful_web_services.jpa.PostRepository;
import com.in28minutes.rest.webservices.restful_web_services.jpa.UserRepository;
import jakarta.validation.Valid;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.Optional;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
public class UserJpaResource {
    //private UserDAOService service;
    private UserRepository userRepository;
    private PostRepository postRepository;
    public UserJpaResource(UserRepository userRepository,PostRepository postRepository) {
        //this.service = service;
        this.userRepository = userRepository;
        this.postRepository = postRepository;
    }
    @GetMapping(path = "jpa/users")
    public List<User> retrieveAllUsers(){
        return userRepository.findAll();
    }

    @GetMapping(path = "jpa/users/{id}")
    public EntityModel<User> retrieveUserById(@PathVariable int id){
        Optional<User> user = userRepository.findById(id);
        if(user.isEmpty())
        {
            throw new UserNotFoundException("id:"+id);
        }
        EntityModel<User> entityModel = EntityModel.of(user.get());
        WebMvcLinkBuilder Link = linkTo(methodOn(this.getClass()).retrieveAllUsers());
        entityModel.add(Link.withRel("all-users"));
         return entityModel;
    }


    @PostMapping(path= "jpa/users")
    public ResponseEntity<Object> createUser(@Valid @RequestBody User user)
    {
            User savedUser = userRepository.save(user);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(savedUser.getId()).toUri();
        return ResponseEntity.created(location).build();
    }
    @DeleteMapping(path = "jpa/users/{id}")
    public void DeleteUserById(@PathVariable int id){
        userRepository.deleteById(id);
    }
    @GetMapping(path = "jpa/users/posts/{id}")
    public List<Post> retrievePostsForAUser(@PathVariable int id){
        Optional<User> user = userRepository.findById(id);
        if(user.isEmpty())
        {
            throw new UserNotFoundException("id:"+id);
        }
        return user.get().getPosts();
        //userRepository.deleteById(id);
    }
    @PostMapping(path = "jpa/users/posts/{id}")
    public ResponseEntity<Object> createPostsForAUser(@PathVariable int id,@Valid @RequestBody Post post){
        Optional<User> user = userRepository.findById(id);
        if(user.isEmpty())
        {
            throw new UserNotFoundException("id:"+id);
        }
        post.setUser(user.get());
        Post savedPost = postRepository.save(post);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(savedPost.getId()).toUri();
        return ResponseEntity.created(location).build();
        // user.get().getPosts();
        //userRepository.deleteById(id);
    }
}
