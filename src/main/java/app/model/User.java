package app.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "users")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class User extends AbstractEntity {

    @Column(name = "is_moderator", columnDefinition = "TINYINT", nullable = false)
    private byte isModerator;

    @Column(name = "reg_time", nullable = false)
    private LocalDateTime registerTime;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "email", nullable = false, unique = true)
    private String email;

    @Column(name = "password", nullable = false)
    private String password;

    @Column(name = "code")
    private String code;

    @Column(name = "photo", columnDefinition = "TEXT")
    private String photoLink;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<PostVote> listVotes;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<PostComment> listComments;

    @OneToMany(mappedBy = "user")
    private List<Post> listPosts;

    @OneToMany(mappedBy = "moderator")
    private List<Post> listModPosts;

}
