package com.example.myprojrcts.firstwebapp12.Security;

import org.springframework.data.domain.Example;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.FluentQuery;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.function.Function;
@Repository
public interface UserRepository extends JpaRepository<User,Long>
{
    Optional<User> findByUserName(String username);
    Optional<User>deleteByEmail(String Email);
}
