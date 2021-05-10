package com.Server.dto.Response;

import lombok.*;

import java.io.Serializable;
import java.util.List;

@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@EqualsAndHashCode
@ToString
public class UserResponse implements Serializable {

    /**id*/
    private Long id;

    /**username*/
    private String username;

    /**email*/
    private String email;

    /**roles*/
    private List<RoleResponse> roles;
}
