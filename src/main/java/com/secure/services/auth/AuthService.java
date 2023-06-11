package com.secure.services.auth;

import com.secure.dto.request.SignupDTO;
import com.secure.dto.request.UserDTO;

public interface AuthService {
    UserDTO createUser(SignupDTO signupDTO);
}
