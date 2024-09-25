package com.myProject.ecommerce.service;


import com.myProject.ecommerce.Exception.UserException;
import com.myProject.ecommerce.Model.User;

public interface UserService {

    public User findUserById(Long userId) throws UserException;

    public User findUserProfileByJwt(String jwt) throws UserException;


}
