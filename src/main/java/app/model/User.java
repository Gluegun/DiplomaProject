package app.model;

import lombok.Data;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "users")
@Data
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "is_moderator", columnDefinition = "TINYINT", nullable = false)
//    @JsonProperty("is_moderator")
    private byte isModerator;

    @Column(name = "reg_time", nullable = false)
//    @JsonProperty("reg_time")
    private Date registerTime;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String email;

//    @JsonIgnore
    @Column(nullable = false)
    private String password;

//    @JsonIgnore
    private String code;

    @Column(name = "photo", columnDefinition = "TEXT")
//    @JsonProperty("photo")
    private String photoLink;

    @OneToMany(mappedBy = "user")
//    @JsonBackReference
    private List<Post> posts = new ArrayList<>();

    @OneToMany(mappedBy = "user")
    private List<PostComment> postComments = new ArrayList<>();

    public User() {

    }

    public User(byte isModerator, Date registerTime,
                String name, String email,
                String password, String code,
                String photoLink) {
        this.isModerator = isModerator;
        this.registerTime = registerTime;
        this.name = name;
        this.email = email;
        this.password = password;
        this.code = code;
        this.photoLink = photoLink;
    }
}
