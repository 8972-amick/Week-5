package com.user.model.dao;

import com.user.model.User;

public interface UserInterface {
    boolean registerUser(User user);
    User loginUser(String email, String password);
}
