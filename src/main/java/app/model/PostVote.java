package app.model;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "post_votes")
@Data
public class PostVote {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne
    @JoinColumn(name = "post_id")
    private Post post;

    private Date time;

    @Column(columnDefinition = "TINYINT")
    private byte value;

    public PostVote() {

    }

    public PostVote(User user, Post post, Date time, byte value) {
        this.user = user;
        this.post = post;
        this.time = time;
        this.value = value;
    }
}
