package bobcurrie.playground.MeetUp.model;

import lombok.*;

import javax.persistence.*;
import java.util.Set;

@Getter
@Setter

@Data
@NoArgsConstructor
@RequiredArgsConstructor
@Entity
@Table(name="user_group")
public class Group {
    @Id
    @GeneratedValue
    private Long Id;
    @NonNull
    private String name;
    private String address;
    private String city;
    private String postcode;
    private String country;
    @ManyToOne(cascade=CascadeType.PERSIST)
    private User user;

    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private Set<Event> events;


}
