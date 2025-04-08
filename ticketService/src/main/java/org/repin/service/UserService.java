package org.repin.service;

import org.repin.dto.UserInfoDto;
import org.repin.enums.Roles;
import org.repin.model.User;
import org.repin.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;


@Service
public class UserService  {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public UserService(UserRepository userRepository,
                       PasswordEncoder passwordEncoder){
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public void saveUser(UserInfoDto userInfoDto) throws Exception {
        User user = new User();
        user.setLogin(userInfoDto.getUsername());

        user.setPassword(passwordEncoder.encode(userInfoDto.getPassword()));

        user.setFullName(userInfoDto.getName());

        user.setRole(Roles.USER);

        userRepository.save(user);
    }


}
