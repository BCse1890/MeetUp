package bobcurrie.playground.MeetUp;

import bobcurrie.playground.MeetUp.model.Group;
import bobcurrie.playground.MeetUp.repository.GroupRepository;
import bobcurrie.playground.MeetUp.repository.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.stream.Stream;

@Component
public class Initializer implements CommandLineRunner {

    private final GroupRepository groupRepository;

    public Initializer(GroupRepository groupRepository, UserRepository userRepository) {
        this.groupRepository = groupRepository;
    }

    @Override
    public void run(String... strings) {
        Stream.of("Keynsham dojo", "Lawrence Weston dojo", "Filton dojo", "Emerson's Green dojo",
                "Yate dojo").forEach(name -> groupRepository.save(new Group(name))
        );

//        Group dojoList = repository.findByName("Keynsham dojo");
//        Event e = Event.builder().title("Full Stack Reactive")
//                .description("Reactive with Spring Boot + React")
//                .date(Instant.parse("2018-12-12T18:00:00:000Z"))
//                .build();
//        dojoList.setEvents(Collections.singleton(e));
//        repository.save(dojoList);
//
//        repository.findAll().forEach(System.out::println);
    }
}
