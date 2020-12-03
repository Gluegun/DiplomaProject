package app.model;

import app.model.enums.ModerationStatus;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Entity
@Data
@Table(name = "posts")
public class Post extends AbstractEntity {

    @Column(name = "is_active", nullable = false)
    private byte isActive;

    @Enumerated(EnumType.STRING)
    @Column(name = "moderation_status", nullable = false)
    private ModerationStatus moderationStatus = ModerationStatus.NEW;

    @ManyToOne
    @JoinColumn(name = "moderator_id", referencedColumnName = "id")
    private User moderator;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @Column(name = "time", nullable = false)
    private LocalDateTime time;

    @Column(name = "title", nullable = false)
    private String title;

    @Column(name = "text", nullable = false, columnDefinition = "text")
    private String text;

    @Column(name = "view_count", nullable = false)
    private int viewCount;

    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE}, fetch = FetchType.LAZY)
    @JoinTable(name = "tag2post", joinColumns = {@JoinColumn(name = "post_id")}, inverseJoinColumns = {
            @JoinColumn(name = "tag_id")})
    private List<Tag> listTags;

    @OneToMany(mappedBy = "post", cascade = CascadeType.ALL)
    private List<PostVote> listVotes;

    @OneToMany(mappedBy = "post", cascade = CascadeType.ALL)
    private List<PostComment> listComments;

}
