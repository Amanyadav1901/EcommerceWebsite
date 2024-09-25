package com.myProject.ecommerce.service;

import com.myProject.ecommerce.Exception.UserException;
import com.myProject.ecommerce.Model.User;
import com.myProject.ecommerce.Repository.UserRepository;
import com.myProject.ecommerce.config.JwtProvider;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserServiceImplementation implements UserService {

    private UserRepository userRepository;
    private JwtProvider jwtProvider;

    public UserServiceImplementation(UserRepository userRepository, JwtProvider jwtProvider) {
        this.userRepository = userRepository;
        this.jwtProvider = jwtProvider;
    }

    @Override
    public User findUserById(Long userId) throws UserException {

        Optional<User> user = userRepository.findById(userId);
        if(user.isPresent())return user.get();

        throw new UserException("User Not Found with id: "+userId);
    }

    @Override
    public User findUserProfileByJwt(String jwt) throws UserException {
        String email = jwtProvider.getEmailFromToken(jwt);

        User user = userRepository.findByEmail(email);

        if(user == null)throw new UserException("User Not Found with email: "+email);

        return user;
    }
}
