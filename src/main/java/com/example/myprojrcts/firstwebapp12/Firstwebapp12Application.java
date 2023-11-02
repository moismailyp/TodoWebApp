package com.example.myprojrcts.firstwebapp12;

import com.example.myprojrcts.firstwebapp12.Security.User;
import com.example.myprojrcts.firstwebapp12.Security.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;

@SpringBootApplication
public class Firstwebapp12Application {

	public static void main(String[] args) {
		SpringApplication.run(Firstwebapp12Application.class, args);
	}
	@Bean

	CommandLineRunner commandLineRunner(UserRepository userRepository, PasswordEncoder encoder) {
		return args->{

			User user=User.builder().userName("user")
					.email("user@gmail.com")
					.password(encoder.encode("password1"))
					.roles("ROLE_USER")
					.build();

			User admin=User.builder().userName("admin")
					.email("admin@gmail.com")
					.password(encoder.encode("123"))
					.roles("ROLE_USER,ROLE_ADMIN")
					.build();

//			userRepository.save(user);
//			userRepository.save(admin);

		};
	}

}

