package com.stoneage.microservices.rettiwtservice.user;

import static java.util.Comparator.comparingLong;

import java.net.URI;
import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.stoneage.microservices.rettiwtservice.exception.NotFoundException;
import com.stoneage.microservices.rettiwtservice.exception.UserFollowException;

@RestController
public class UserResource 
{
    @Autowired
    private UserRepository userRepository;
    
    @Autowired
    private PostRepository postRepository;

    @GetMapping(path = "/rettiwt-service/users")
    public List<User> retrieveAllUsers()
    {
        return userRepository.findAll();
    }
    
    @GetMapping(path = "/rettiwt-service/users/{userId}")
    public User retrieveUser(@PathVariable Long userId)
    {
        Optional<User> userOptional = userRepository.findById(userId);
        if (!userOptional.isPresent())
        {
            throw new NotFoundException("User not found: " + userId);
        }
        
        return userOptional.get();
    }
    
    @PostMapping(path = "/rettiwt-service/users")
    public ResponseEntity<Object> createUser(@Valid @RequestBody User user)
    {
        User savedUser = userRepository.save(user);
        
        URI uri = ServletUriComponentsBuilder
            .fromCurrentRequest()
            .path("/{userId}")
            .buildAndExpand(savedUser.getId())
            .toUri();
        
        return ResponseEntity.created(uri).build();
    }
    
    @GetMapping(path = "/rettiwt-service/users/{userId}/posts")
    public List<Post> retrieveAllUserPosts(@PathVariable Long userId)
    {
        Optional<User> userOptional = userRepository.findById(userId);
        if (!userOptional.isPresent())
        {
            throw new NotFoundException("User not found: " + userId);
        }
        
        return userOptional.get().getPosts();
    }
    
    @PostMapping(path = "/rettiwt-service/users/{userId}/posts")
    public ResponseEntity<Object> createPost(
            @PathVariable Long userId, 
            @RequestBody @Valid Post post)
    {
        Optional<User> userOptional = userRepository.findById(userId);
        if (!userOptional.isPresent())
        {
            throw new NotFoundException("User not found: " + userId);
        }
        
        User user = userOptional.get();
        post.setUser(user);
        
        postRepository.save(post);

        URI uri = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{postId}")
                .buildAndExpand(post.getId())
                .toUri();
            
        return ResponseEntity.created(uri).build();
    }
    
    @PostMapping(path = "/rettiwt-service/users/{userId}/follows/{followedId}")
    public ResponseEntity<Object> createFollow(@PathVariable Long userId,
            @PathVariable Long followedId)
    {
        if (userId.equals(followedId))
        {
            throw new UserFollowException(
                    "User can't follow itself: " + userId);
        }
                
        Optional<User> userOptional = userRepository.findById(userId);
        if (!userOptional.isPresent())
        {
            throw new NotFoundException("User not found: " + userId);
        }
        
        Optional<User> followedOptional = userRepository.findById(followedId);
        if (!followedOptional.isPresent())
        {
            throw new NotFoundException("User not found: " + followedId);
        }
        
        User user = userOptional.get();
        User followed = followedOptional.get();
        user.getFollowedByMe().add(followed);
        
        userRepository.save(user);

        URI uri = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .build()
                .toUri();
                
        return ResponseEntity.created(uri).build();
    }
    
    @DeleteMapping(path = 
            "/rettiwt-service/users/{userId}/follows/{followedId}")
    public void deleteFollow(@PathVariable Long userId, 
            @PathVariable Long followedId)
    {
        if (userId.equals(followedId))
        {
            throw new UserFollowException(
                    "User can't unfollow itself: " + userId);
        }
        
        Optional<User> userOptional = userRepository.findById(userId);
        if (!userOptional.isPresent())
        {
            throw new NotFoundException("User not found: " + userId);
        }
        
        Optional<User> followedOptional = userRepository.findById(followedId);
        if (!followedOptional.isPresent())
        {
            throw new NotFoundException("User not found: " + followedId);
        }
        
        User user = userOptional.get();
        User followed = followedOptional.get();
        user.getFollowedByMe().remove(followed);
        
        userRepository.save(user);
    }
    
    @GetMapping(path = "/rettiwt-service/users/{userId}/follows/posts")
    public List<Post> retrieveAllFollowerPosts(@PathVariable Long userId)
    {
        Optional<User> userOptional = userRepository.findById(userId);
        if (!userOptional.isPresent())
        {
            throw new NotFoundException("User not found: " + userId);
        }
        
        List<Post> followerPosts = userOptional.get().getFollowedByMe().stream()
                .map(User::getPosts)
                .flatMap(Collection::stream)
                .sorted(comparingLong(Post::getId).reversed())
                .collect(Collectors.toList());
                
        return followerPosts;
    }
}
