package com.example.myprojrcts.firstwebapp12.Security;

import jakarta.transaction.Transactional;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.function.Predicate;
@Data
@Service
@RequiredArgsConstructor
public class UserService {
//    public static List<User> userdetailsList=new ArrayList<>();

//    public List<User> userdetails()
//    {
//        return userdetailsList;
//    }
////    public void addUser(int id,String userid, String username,String batchNo, String domain)
////    {
////        User user=new User(id,userid,username,batchNo,domain);
////                    userdetailsList.add(user);
////    }
//    public void DeleteUser(String userid)
//    {
//        Predicate<? super User> predicate= userdetailsList->userdetailsList.getUserName ()==userid;
//        userdetailsList.removeIf(predicate);

    //    }
    final private UserRepository userRepository;
    final private PasswordEncoder passwordEncoder;
     int flag = 0;

    public boolean createUser(CreateUserRequest createUserRequest) {
        Optional<User> userByName = userRepository.findByUserName(createUserRequest.getUsername());

        if (userByName.isPresent()) {
             this.flag=1;
             return false;
        }
        if (createUserRequest.getPassword().equals(createUserRequest.getConfirmPassword())) {
            User newUser = User.builder()
                    .userName(createUserRequest.getUsername())
                    .email(createUserRequest.getEmail())
                    .password(passwordEncoder.encode(createUserRequest.getPassword()))
                    .roles("ROLE_USER")
                    .build();

            userRepository.save(newUser);
            return true;
        }

        return false;
    }
    @Transactional
    public void deleteUserByEmail(String email) {
        userRepository.deleteByEmail(email);
    }

}


