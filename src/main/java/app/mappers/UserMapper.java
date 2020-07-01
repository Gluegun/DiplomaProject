package app.mappers;

import app.dto.UserDTO;
import app.model.User;
import org.mapstruct.Mapper;

@Mapper
public interface UserMapper {

    UserDTO toDTO(User user);

}
