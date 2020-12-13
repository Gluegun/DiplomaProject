package app.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PostDtoWithComments {

    private int id;
    private Long timestamp;
    @JsonProperty(value = "active")
    private boolean isActive;
    private UserDto user;
    private String title;
    private String text;
    private int likeCount;
    private int dislikeCount;
    private int viewCount;
    private List<CommentDto> comments;
    private List<String> tags;

}
