package app.model;

import app.model.enums.ModerationStatus;
import lombok.Data;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "posts")
@Data
public class Post {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "is_active", columnDefinition = "TINYINT", nullable = false)
//    @JsonProperty("is_active")
    private byte isActive;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private ModerationStatus moderationStatus = ModerationStatus.NEW;

    @Column(name = "moderator_id")
//    @JsonProperty("moderator_id")
    private Integer moderatorId;

    @ManyToOne
    @JoinColumn(name = "user_id")
//    @JsonManagedReference
    private User user;

    @Column(nullable = false)
    private Date time;

    @Column(nullable = false)
    private String title;

    @Column(name = "text", length = 65535, columnDefinition = "TEXT", nullable = false)
    private String text;

    @Column(name = "view_count")
//    @JsonProperty("view_count")
    private int viewCount;

    @OneToMany(mappedBy = "post")
    private List<PostComment> postComments = new ArrayList<>();

    @OneToOne (mappedBy = "post")
    private Tag2Post tag2Post;

    public Post() {

    }

    public Post(byte isActive, ModerationStatus moderationStatus, int moderatorId,
                User user, Date time, String title, String text, int viewCount) {
        this.isActive = isActive;
        this.moderationStatus = moderationStatus;
        this.moderatorId = moderatorId;
        this.user = user;
        this.time = time;
        this.title = title;
        this.text = text;
        this.viewCount = viewCount;
    }

    @Override
    public String toString() {
        return "Post{" +
                title +
                "}";
    }
}
