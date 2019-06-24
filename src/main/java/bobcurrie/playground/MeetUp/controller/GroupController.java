package bobcurrie.playground.MeetUp.controller;

import bobcurrie.playground.MeetUp.model.Group;
import bobcurrie.playground.MeetUp.repository.GroupRepository;
import bobcurrie.playground.MeetUp.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.persistence.Id;
import javax.validation.Valid;
import java.net.URI;
import java.net.URISyntaxException;
import java.security.Principal;
import java.util.Collection;
import java.util.Optional;

@Slf4j
@RestController
@RequestMapping("/api")

public class GroupController {

    private final Logger log =
            LoggerFactory.getLogger(GroupController.class);
    @Autowired
    private GroupRepository groupRepository;
    private UserRepository userRepository;

    public GroupController(GroupRepository groupRepository, UserRepository userRepository) {
        this.groupRepository = groupRepository;
        this.userRepository = userRepository;
    }

    @GetMapping("/groups")
    Collection<Group> groups() {
        return groupRepository.findAll();
    }

    @GetMapping("/group/{Id}")
    ResponseEntity<?> getGroup(@PathVariable Long Id) {
        Optional<Group> group = groupRepository.findById(Id);
        return group.map(response -> ResponseEntity.ok().body(response))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PostMapping("/group")
    ResponseEntity<Group> createGroup(@Valid @RequestBody Group group) throws URISyntaxException {
        log.info("Request to create group: {}", group);
        Group result = groupRepository.save(group);
        return ResponseEntity.created(new URI("api/group/" + result.getId())).body(result);
    }

    @PutMapping("/group")
    ResponseEntity<Group> updateGroup(@Valid @RequestBody Group group) {
        log.info("Request to update group: {}", group);
        Group result = groupRepository.save(group);
        return ResponseEntity.ok().body(result);
    }

    @DeleteMapping("/group/{Id}")
    public ResponseEntity<?> deleteGroup(@PathVariable Long Id) {
        log.info("Request to delete group: {}", Id);
        groupRepository.deleteById(Id);
        return ResponseEntity.ok().build();
    }
}
