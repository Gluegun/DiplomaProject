package app.dto;

import app.model.Role;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserDto {

    private int id;
    private String name;
    @JsonIgnore
    private byte isModerator;
    @JsonIgnore
    private String password;
    @JsonIgnore
    private String userName;
    private Set<Role> roles;

}
