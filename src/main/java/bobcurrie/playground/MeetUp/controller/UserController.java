package bobcurrie.playground.MeetUp.controller;


import bobcurrie.playground.MeetUp.model.User;
import bobcurrie.playground.MeetUp.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.Collection;
import java.util.Optional;

@Slf4j
@RestController
@RequestMapping("/api")

public class UserController {

    private final Logger log =
            LoggerFactory.getLogger(GroupController.class);
    @Autowired
    UserRepository userRepository;

    @GetMapping("/users")
    Collection<User> users() {
        return userRepository.findAll();
    }

    @GetMapping("/user/{Id}")
    ResponseEntity<User> getUser(@PathVariable String Id) {
        Optional<User> user = userRepository.findById(Id);
        return user.map(response -> ResponseEntity.ok().body(response))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PostMapping("/user")
    ResponseEntity<User> CreateUser(@Valid @RequestBody User user) throws URISyntaxException {
        User result = userRepository.save(user);
        return ResponseEntity.created(new URI("api/user/" + result.getId())).body(user);
    }

    @PutMapping("/user/{Id}")
    ResponseEntity<User> updateUser(@Valid @RequestBody User user) throws URISyntaxException {
        User result = userRepository.save(user);
        return ResponseEntity.ok().body(result);
    }

    @DeleteMapping("user/{Id}")
    ResponseEntity<User> deleteUser(@Valid @RequestBody String Id) {
        log.info("Request to delete user: {}", Id);
        userRepository.deleteById(Id);
        return ResponseEntity.ok().build();
    }
  }


