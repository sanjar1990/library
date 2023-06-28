package org.example.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.enums.ProfileStatus;
import org.example.enums.Role;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProfileDto {
    private Integer id;
    private String name;
    private String surname;
    private String login;
    private String password;
    private String phone;
    private ProfileStatus status;
    private Role role;
    private LocalDateTime createdDate;
}
