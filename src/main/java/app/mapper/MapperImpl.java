package app.mapper;

import app.dto.*;
import app.model.*;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.*;
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
                .roles(getRolesByUserStatus(user.getIsModerator()))
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
    public TagDto toTagDto(Tag tag) {

        return TagDto.builder()
                .name(tag.getName())
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


    @Override
    public PostDtoWithComments toPostDtoWithComments(Post post) {

        return PostDtoWithComments.builder()
                .id(post.getId())
                .timestamp(convertLocalDateTimeToTimeStamp(post.getTime()))
                .isActive(byteToBool(post.getIsActive()))
                .user(toUserDto(post.getUser()))
                .title(post.getTitle())
                .text(post.getText())
                .likeCount(likeCount(post.getListVotes()))
                .dislikeCount(dislikeCount(post.getListVotes()))
                .viewCount(post.getViewCount())
                .tags(getTagsFromPost(post.getListTags()))
                .comments(toListCommentsDto(post.getListComments()))

                .build();

    }

    @Override
    public PostForYear toPostForYear(List<Post> allPosts, List<Post> requiredPosts) {

        return PostForYear.builder()
                .years(getYears(allPosts))
                .posts(getPosts(requiredPosts))
                .build();

    }

    private Map<String, Integer> getPosts(List<Post> posts) {

        Map<String, Integer> yearsAndPosts = new HashMap<>();

        for (Post post : posts) {

            int day = post.getTime().getDayOfMonth();
            int month = post.getTime().getMonth().getValue();
            int year = post.getTime().getYear();

            String postTime = String.format("%s-%s-%s", year, month, day);

            if (!yearsAndPosts.containsKey(postTime)) {
                yearsAndPosts.put(postTime, 1);
            } else yearsAndPosts.put(postTime, yearsAndPosts.get(postTime) + 1);

        }

        return new TreeMap<>(yearsAndPosts);

    }

    private List<Integer> getYears(List<Post> posts) {

        Set<Integer> years = new HashSet<>();

        for (Post post : posts) {
            years.add(post.getTime().getYear());
        }

        return new ArrayList<>(years);


    }


    private Set<Role> getRolesByUserStatus(byte isModerator) {

        Set<Role> roles = new HashSet<>();
        if (isModerator == 1) {
            roles.add(Role.ADMIN);
        }
        roles.add(Role.USER);
        return roles;


    }

    private List<String> getTagsFromPost(List<Tag> tags) {

        return tags.stream().map(Tag::getName).collect(Collectors.toList());


    }

    private Long convertLocalDateTimeToTimeStamp(LocalDateTime dateToConvert) {
        ZonedDateTime zdt = dateToConvert.atZone(ZoneId.systemDefault());
        return zdt.toInstant().toEpochMilli() / 1000;

    }

    private boolean byteToBool(byte isActive) {

        return isActive > 0;

    }

    private List<CommentDto> toListCommentsDto(List<PostComment> comments) {

        List<CommentDto> commentDtos = new ArrayList<>();

        for (PostComment comment : comments) {

            commentDtos.add(toCommentsDto(comment));

        }

        return commentDtos;

    }

    private CommentDto toCommentsDto(PostComment comment) {

        return CommentDto.builder()
                .id(comment.getId())
                .timestamp(convertLocalDateTimeToTimeStamp(comment.getTime()))
                .text(comment.getText())
                .user(toUserDtoWithPhoto(comment.getUser()))
                .build();

    }

    private UserDtoWithPhoto toUserDtoWithPhoto(User user) {

        String photoLink = "";
        if (!user.getPhotoLink().isEmpty() || user.getPhotoLink() == null) {
            photoLink = user.getPhotoLink();
        }

        return UserDtoWithPhoto.builder()
                .id(user.getId())
                .name(user.getName())
                .photo(photoLink)
                .build();

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
