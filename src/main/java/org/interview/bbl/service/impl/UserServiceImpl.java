package org.interview.bbl.service.impl;

import lombok.RequiredArgsConstructor;
import org.interview.bbl.dto.request.UserRequest;
import org.interview.bbl.dto.response.UserResponse;
import org.interview.bbl.exception.UserNotFoundException;
import org.interview.bbl.mapper.UserMapper;
import org.interview.bbl.model.entity.User;
import org.interview.bbl.repository.UserRepository;
import org.interview.bbl.service.UserService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;

    @Override
    @Transactional(readOnly = true)
    public List<UserResponse> getAllUsers() {
        return userRepository.findAll()
                .stream()
                .map(userMapper::toResponse)
                .toList();
    }

    @Override
    @Transactional(readOnly = true)
    public UserResponse getUserById(Long userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new UserNotFoundException(userId));
        return userMapper.toResponse(user);
    }

    @Override
    @Transactional
    public UserResponse createUser(UserRequest request) {
        User user = userMapper.toEntity(request);
        User saved = userRepository.save(user);
        return userMapper.toResponse(saved);
    }

    @Override
    @Transactional
    public UserResponse updateUser(Long userId, UserRequest request) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new UserNotFoundException(userId));
        userMapper.updateEntity(user, request);
        User saved = userRepository.save(user);
        return userMapper.toResponse(saved);
    }

    @Override
    @Transactional
    public void deleteUser(Long userId) {
        if (!userRepository.existsById(userId)) {
            throw new UserNotFoundException(userId);
        }
        //should use soft delete in prd
        //set status INACTIVE
        userRepository.deleteById(userId);
    }

    @Override
    @Transactional
    public List<UserResponse> createUserList(List<UserRequest> requests) {
        List<User> users = requests.stream()
                .map(userMapper::toEntity)
                .toList();
        return userRepository.saveAll(users).stream()
                .map(userMapper::toResponse)
                .toList();
    }
}

