package org.example.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.enums.ProfileStatus;
import org.example.enums.Role;

import javax.persistence.*;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "profile")
public class ProfileEntity {
   @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
   private Integer id;
   @Column(length = 20)
   private String name;
   @Column(length = 20)
   private String surname;
   @Column(length =20,unique = true)
   private String login;
   @Column
   private String password;
   @Column(length = 13, unique = true)
   private String phone;
   @Column
   @Enumerated(EnumType.STRING)
   private ProfileStatus status;
   @Column
   @Enumerated(EnumType.STRING)
   private Role role;
   @Column(name = "created_date")
   private LocalDateTime createdDate;


}
