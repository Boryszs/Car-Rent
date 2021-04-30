package com.Server.dto.Response;

import com.Server.model.Role;
import lombok.*;

import java.io.Serializable;
import java.util.List;

@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class UserResponse implements Serializable {

    /**id*/
    private Long id;

    /**username*/
    private String username;

    /**email*/
    private String email;

    /**roles*/
    private List<Role> roles;
}
