package app.mapper;

import app.dto.*;
import app.model.Post;
import app.model.Tag;
import app.model.User;

import java.util.List;

public interface Mapper {

    PostDto toPostDto(Post post);

    UserDto toUserDto(User user);

    PostDtoWithComments toPostDtoWithComments(Post post);

    GeneralPostDto toGeneralPostDto(List<Post> posts);

    PostForYear toPostForYear(List<Post> allPosts, List<Post> posts);

    Post toPost(PostForLikes postForLikes);

    TagDto toTagDto(Tag tag);

}
