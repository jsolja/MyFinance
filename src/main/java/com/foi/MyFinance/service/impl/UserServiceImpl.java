package com.foi.MyFinance.service.impl;

import com.foi.MyFinance.entity.RoleEntity;
import com.foi.MyFinance.entity.UserEntity;
import com.foi.MyFinance.model.UserModel;
import com.foi.MyFinance.repository.RoleRepository;
import com.foi.MyFinance.repository.UserRepository;
import com.foi.MyFinance.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class UserServiceImpl
        implements UserService, UserDetailsService
{
    private static final String USERNAME_NOT_FOUND_EXCEPTION_MESSAGE = "Invalid username or password.";

    private static final String ROLE_USER = "ROLE_USER";

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public Optional<UserEntity> findByUsername(final String username)
    {
        return userRepository.findByUsername(username);
    }

    @Override
    public UserEntity createUser(final UserModel userModel)
    {
        final UserEntity newUser = new UserEntity();
        newUser.setActive(true); //we will change that later when we implemented user activation
        newUser.setEmail(userModel.getEmail());
        newUser.setFirstName(userModel.getFirstName());
        newUser.setLastName(userModel.getLastName());
        newUser.setPassword(passwordEncoder.encode(userModel.getPassword()));
        newUser.setUsername(userModel.getUsername());
        final Optional<RoleEntity> roleEntity = roleRepository.findByRole(ROLE_USER);
        roleEntity.ifPresent(newUser::setRole);
        return userRepository.save(newUser);
    }

    @Override
    public UserDetails loadUserByUsername(final String username)
    {
        final Optional<UserEntity> optionalUserEntity = userRepository.findByUsername(username);
        if (!optionalUserEntity.isPresent())
        {
            throw new UsernameNotFoundException(USERNAME_NOT_FOUND_EXCEPTION_MESSAGE);
        }
        return optionalUserEntity.get();
    }
}
