package com.example.myprojrcts.firstwebapp12.Security;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class CreateUserRequest {

        private String username;
        private String email;
        private String password;
        private String confirmPassword;


}
