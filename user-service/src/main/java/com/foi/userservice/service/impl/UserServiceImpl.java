package com.foi.userservice.service.impl;

import com.foi.userservice.entity.RoleEntity;
import com.foi.userservice.entity.UserEntity;
import com.foi.userservice.model.UserModel;
import com.foi.userservice.repository.RoleRepository;
import com.foi.userservice.repository.UserRepository;
import com.foi.userservice.service.UserService;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
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
    public Optional<UserEntity> findByEmail(final String email)
    {
        return userRepository.findByEmail(email);
    }

    @Override
    public Optional<UserEntity> findByResetToken(final String token)
    {
        return userRepository.findByToken(token);
    }

    @Override
    public void createToken(final UserEntity userEntity)
    {
        userEntity.setToken(UUID.randomUUID().toString());
        userRepository.save(userEntity);
    }

    @Override
    public void resetUserPassword(final UserEntity userEntity, final String newPassword)
    {
        userEntity.setPassword(passwordEncoder.encode(newPassword));
        userEntity.setToken(null);
        userRepository.save(userEntity);
    }

    @Override
    public void activateUser(final UserEntity userEntity)
    {
        userEntity.setActive(true);
        userEntity.setToken(null);
        userRepository.save(userEntity);
    }

    @Override
    public UserEntity createUser(final UserModel userModel)
    {
        final UserEntity newUser = new UserEntity();
        newUser.setActive(false);
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
    public UserEntity getUserEntity()
    {
        final Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return findByUsername(authentication.getName()).get();
    }

    @Override
    public void updateUser(final UserModel userModel)
    {
        final UserEntity userEntity = getUserEntity();
        if (StringUtils.isNotEmpty(userModel.getUsername()))
        {
            userEntity.setUsername(userModel.getUsername());
        }
        if (StringUtils.isNotEmpty(userModel.getFirstName()))
        {
            userEntity.setFirstName(userModel.getFirstName());
        }
        if (StringUtils.isNotEmpty(userModel.getLastName()))
        {
            userEntity.setLastName(userModel.getLastName());
        }
        if (StringUtils.isNotEmpty(userModel.getEmail()))
        {
            userEntity.setEmail(userModel.getEmail());
        }
        if (StringUtils.isNotEmpty(userModel.getPassword()))
        {
            userEntity.setPassword(passwordEncoder.encode(userModel.getPassword()));
        }
        if (userModel.getBalance() != 0)
        {
            userEntity.setBalance((float) userModel.getBalance());
        }
        userRepository.save(userEntity);
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
