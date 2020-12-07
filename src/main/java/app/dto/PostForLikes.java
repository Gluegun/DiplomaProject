package app.dto;

import app.model.Post;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PostForLikes implements Comparable<PostForLikes> {

    private Post post;
    private int likesAmount;
    private int dislikesAmount;

    @Override
    public int compareTo(PostForLikes o) {

        return Integer.compare(o.getLikesAmount(), this.getLikesAmount());

    }
}
