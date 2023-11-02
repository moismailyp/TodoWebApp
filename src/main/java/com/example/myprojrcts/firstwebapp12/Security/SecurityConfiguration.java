package com.example.myprojrcts.firstwebapp12.Security;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.WebSecurityConfigurer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfiguration;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import static org.springframework.security.config.Customizer.withDefaults;


import java.util.function.Function;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration  {
private final JpaUserdetailsService jpaUserdetailsService;

public SecurityConfiguration (JpaUserdetailsService jpaUserdetailsService)

{
    this.jpaUserdetailsService=jpaUserdetailsService;
}

//
//
//
//        @Bean
//   InMemoryUserDetailsManager inMemoryUserDetailsManagermethod()
//
//        {
//            Function <String ,String> passwordEncoder=input->passwordEncoder().encode(input);
//            UserDetails userDetails=User.builder()
//                    .passwordEncoder(passwordEncoder)
//                    .username("ismail")
//                    .password("sgasf")
//                    .authorities("ROLE_ADMIN")
//                    .build();
//            UserDetails admin=User.builder()
//                    .passwordEncoder(passwordEncoder)
//                    .username("shahid")
//                    .password("123")
//                    .roles("user")
//                    .build();
//                    return new InMemoryUserDetailsManager(userDetails);
//
//
//        }
        @Bean
    public PasswordEncoder passwordEncoder()
        {
            return new BCryptPasswordEncoder();
        }
        @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception
        {
            httpSecurity
                    .authorizeRequests(authorizeRequests -> authorizeRequests
                                    .requestMatchers(new AntPathRequestMatcher("/signup"))
                                    .permitAll()
                                    .anyRequest()
                                    .authenticated())
                                    .userDetailsService(jpaUserdetailsService)
                                    .formLogin()
                                    .loginPage("/login")
                                    .permitAll();

            return httpSecurity.build();
        }
    @Bean
    public AuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider daoAuthenticationProvider=new DaoAuthenticationProvider();
        daoAuthenticationProvider.setUserDetailsService(jpaUserdetailsService);
        daoAuthenticationProvider.setPasswordEncoder(passwordEncoder());
        return daoAuthenticationProvider;
    }
}
