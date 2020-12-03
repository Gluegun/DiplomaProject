package app.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class GeneralPostDto {

    private int count;
    private List<PostDto> posts = new ArrayList<>();

    public void addPost(PostDto post) {
        posts.add(post);
        count = posts.size();
    }
}
