package app.model;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Entity
@Data
@Table(name = "tags")
@NoArgsConstructor
public class Tag extends AbstractEntity {

    @Column (name = "name", nullable = false)
    private String name;

    @ManyToMany(mappedBy = "listTags")
    private List<Post> listPosts;

}
