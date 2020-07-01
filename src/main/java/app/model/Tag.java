package app.model;

import lombok.Data;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "tags")
@Data
public class Tag {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(nullable = false)
    private String name;

    @OneToMany(mappedBy = "tag")
    List<Tag2Post> tag2Posts = new ArrayList<>();

    public Tag() {

    }

    public Tag(String name) {
        this.name = name;
    }
}
