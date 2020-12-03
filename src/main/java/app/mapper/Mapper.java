package app.mapper;

import app.dto.GeneralPostDto;
import app.dto.PostDto;
import app.dto.UserDto;
import app.model.Post;
import app.model.User;

import java.util.List;

public interface Mapper {

    PostDto toPostDto(Post post);

    UserDto toUserDto(User user);

    GeneralPostDto toGeneralPostDto(List<Post> posts);

}
