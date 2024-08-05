package com.learning.config;

import com.learning.dao.UserDetailsRepository;
import com.learning.entity.UserDetailsEntity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;
@Service
public class MyUserDetailsService implements UserDetailsService {

    public UserDetailsRepository userDetailsRepository;

    public MyUserDetailsService(UserDetailsRepository userDetailsRepository){
        this.userDetailsRepository=userDetailsRepository;
    }
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<UserDetailsEntity> userDetailsEntity = userDetailsRepository.findByUserName(username);
        if(userDetailsEntity.isPresent()){
            UserDetailsEntity user = userDetailsEntity.get();
           return  User.builder()
                    .username(user.getUsername())
                    .password(user.getPassword())
                    .roles(String.valueOf(user.getRoles()))
                    .build();
        }
        throw new UsernameNotFoundException("Invalid UserName or Password");
    }

//    private String[] getRoles(UserDetailsEntity user) {
//        if(Objects.isNull(user.getRoles())){
//            return new String[]{"USER"};
//        }
//        return  user.getRoles().split(",");
//    }
}
