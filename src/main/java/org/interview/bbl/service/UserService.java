package org.interview.bbl.service;

import org.interview.bbl.dto.request.UserRequest;
import org.interview.bbl.dto.response.UserResponse;

import java.util.List;

public interface UserService {

    List<UserResponse> getAllUsers();

    UserResponse getUserById(Long userId);

    UserResponse createUser(UserRequest request);

    UserResponse updateUser(Long userId, UserRequest request);

    void deleteUser(Long userId);

    List<UserResponse> createUserList(List<UserRequest> requests);
}

