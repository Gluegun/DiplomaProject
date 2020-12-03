package app.model;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Entity
@Data
@Table(name = "post_comments")
public class PostComment extends AbstractEntity {

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "parent_id", referencedColumnName = "id")
    private PostComment postComment;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "post_id", referencedColumnName = "id")
    private Post post;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private User user;

    @Column(name = "time", nullable = false)
    private LocalDateTime time;

    @Column(name = "text", columnDefinition = "text", nullable = false)
    private String text;

    @OneToMany(mappedBy = "postComment")
    private List<PostComment> listComments;

    public void addPostComment(PostComment postComment) {
        listComments.add(postComment);
        postComment.setPostComment(this);
    }

    public void removePostComment(PostComment postComment) {
        listComments.remove(postComment);
        postComment.setPostComment(null);
    }


}
