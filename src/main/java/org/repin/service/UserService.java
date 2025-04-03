package org.repin.service;

import org.repin.dto.UserInfoDto;
import org.repin.model.User;
import org.repin.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public void registerUser(UserInfoDto dto) throws Exception {
        User user = User
                .builder()
                .login(dto.getLogin())
                .fullName(dto.getName())
                .password(dto.getPassword())
                .build();

        userRepository.save(user);
    }
}
