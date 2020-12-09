package app.mapper;

import app.dto.GeneralPostDto;
import app.dto.PostDto;
import app.dto.PostForLikes;
import app.dto.UserDto;
import app.model.Post;
import app.model.PostVote;
import app.model.Role;
import app.model.User;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
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
                .isModerator(user.getIsModerator())
                .roles(getRolesFromUserStatus(user.getIsModerator()))
                .password(user.getPassword())
                .userName(user.getEmail())
                .build();

    }


    @Override
    public GeneralPostDto toGeneralPostDto(List<Post> posts) {

        return GeneralPostDto.builder()
                .posts(posts.stream().map(this::toPostDto).collect(Collectors.toList()))
                .count(posts.size())
                .build();

    }


    @Override
    public Post toPost(PostForLikes post) {

        Post builtPost = Post.builder()
                .isActive(post.getPost().getIsActive())
                .listComments(post.getPost().getListComments())
                .listTags(post.getPost().getListTags())
                .listVotes(post.getPost().getListVotes())
                .moderationStatus(post.getPost().getModerationStatus())
                .text(post.getPost().getText())
                .time(post.getPost().getTime())
                .viewCount(post.getPost().getViewCount())
                .moderator(post.getPost().getModerator())
                .title(post.getPost().getTitle())
                .user(post.getPost().getUser())
                .build();

        builtPost.setId(post.getPost().getId());

        return builtPost;
    }

    private Set<Role> getRolesFromUserStatus(byte isModerator) {

        Set<Role> roles = new HashSet<>();
        if (isModerator == 1) {
            roles.add(Role.ADMIN);
        }
        roles.add(Role.USER);
        return roles;


    }

    private Long convertLocalDateTimeToTimeStamp(LocalDateTime dateToConvert) {
        ZonedDateTime zdt = dateToConvert.atZone(ZoneId.systemDefault());
        return zdt.toInstant().toEpochMilli() / 1000;

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
