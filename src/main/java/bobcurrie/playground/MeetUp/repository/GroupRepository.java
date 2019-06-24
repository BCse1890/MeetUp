package bobcurrie.playground.MeetUp.repository;

import bobcurrie.playground.MeetUp.model.Group;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GroupRepository extends JpaRepository<Group, Long> {
    Group findByName(String name);
}
