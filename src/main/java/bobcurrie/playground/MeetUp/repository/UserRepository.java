package bobcurrie.playground.MeetUp.repository;

import bobcurrie.playground.MeetUp.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, String> {

}
