package app.mappers;

import app.dto.PostDTO;
import app.model.Post;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper
public interface PostMapper {

    @Mapping(source = "user", target = "userDTO")
    PostDTO toDTO (Post post);

}
