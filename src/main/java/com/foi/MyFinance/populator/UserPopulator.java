package com.foi.MyFinance.populator;

import com.foi.MyFinance.entity.UserEntity;
import com.foi.MyFinance.model.UserModel;
import org.springframework.stereotype.Component;

@Component
public class UserPopulator
{
    public UserModel populate(final UserEntity userEntity)
    {
        final UserModel userModel = new UserModel();
        userModel.setFirstName(userEntity.getFirstName());
        userModel.setLastName(userEntity.getLastName());
        userModel.setEmail(userEntity.getEmail());
        userModel.setUsername(userEntity.getUsername());
        userModel.setPassword(userEntity.getPassword());
        userModel.setMatchingPassword(userEntity.getPassword());
        userModel.setBalance(userEntity.getBalance());
        return userModel;
    }
}
