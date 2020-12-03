package app.mapper;

import app.dto.GeneralPostDto;
import app.dto.PostDto;
import app.dto.UserDto;
import app.model.Post;
import app.model.PostVote;
import app.model.User;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Transactional
@Component
public class MapperImpl implements Mapper {

    @Override
    public PostDto toPostDto(Post post) {

        return PostDto.builder()
                .id(post.getId())
                .timestamp(convertLocalDateTimeToTimeStamp(post.getTime()))
                .title(post.getTitle())
                .announce(cutString(post.getText()))
                .likeCount(likeCount(post.getListVotes()))
                .dislikeCount(dislikeCount(post.getListVotes()))
                .commentCount(post.getListComments().size())
                .viewCount(post.getViewCount())
                .user(toUserDto(post.getUser()))
                .build();

    }

    @Override
    public UserDto toUserDto(User user) {

        return UserDto.builder()
                .id(user.getId())
                .name(user.getName())
                .build();

    }

    @Override
    public GeneralPostDto toGeneralPostDto(List<Post> posts) {

        return GeneralPostDto.builder()
                .posts(posts.stream().map(this::toPostDto).collect(Collectors.toList()))
                .count(posts.size())
                .build();

    }

    private Long convertLocalDateTimeToTimeStamp(LocalDateTime dateToConvert) {
        ZonedDateTime zdt = dateToConvert.atZone(ZoneId.systemDefault());
        return zdt.toInstant().toEpochMilli();
    }

    private String cutString(String text) {

        if (text.length() > 100) {

            String cutText = text.substring(0, 100);

            if (!text.substring(0, 101).endsWith(" ")) {
                StringBuilder builder = new StringBuilder();
                char[] chars = cutText.toCharArray();
                for (int i = chars.length - 1; i > chars.length - 4; i--) {
                    chars[i] = '.';
                }
                for (char aChar : chars) {
                    builder.append(aChar);
                }
                return builder.toString();

            }
            return cutText;

        } else return text;
    }

    private int likeCount(List<PostVote> votes) {

        int likes = 0;
        for (PostVote vote : votes) {
            if (vote.getValue() == 1) {
                likes++;
            }
        }
        return likes;
    }

    private int dislikeCount(List<PostVote> votes) {

        int dislikes = 0;
        for (PostVote vote : votes) {
            if (vote.getValue() == -1) {
                dislikes++;
            }
        }

        return dislikes;
    }

}
