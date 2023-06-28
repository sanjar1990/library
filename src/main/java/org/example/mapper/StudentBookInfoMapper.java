package org.example.mapper;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.enums.ProfileStatus;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class StudentBookInfoMapper {
    private Integer id;
    private String name;
    private String surname;
    private String login;
    private String phone;
    private ProfileStatus status;
    private Long takenBookCount;
    private String bookOnHandNamesCount;
}
