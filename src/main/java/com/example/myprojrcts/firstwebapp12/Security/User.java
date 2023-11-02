package com.example.myprojrcts.firstwebapp12.Security;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.*;


@AllArgsConstructor
@Entity
@NoArgsConstructor
@Data
@Builder
@Table(name="Userslist")
public class User {

  @Id
  @GeneratedValue
    private Long id;
    private String userName;
    private String email;
    private String password;
    private String roles;



}
