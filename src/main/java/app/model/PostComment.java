package app.model;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "post_comments")
@Data
public class PostComment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "parent_id")
    private Integer parentId;

    @ManyToOne
    @JoinColumn(name = "post_id")
    private Post post;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    private Date time;

    private String text;

    public PostComment() {

    }

    public PostComment(Integer parentId, Post post, User user, Date time, String text) {
        this.parentId = parentId;
        this.post = post;
        this.user = user;
        this.time = time;
        this.text = text;
    }

}
