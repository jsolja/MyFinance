package com.foi.userservice.model;

import com.foi.userservice.entity.UserEntity;

public class UserWithDateModel
{
    private String date;
    private UserEntity userEntity;

    public String getDate()
    {
        return date;
    }

    public void setDate(String date)
    {
        this.date = date;
    }

    public UserEntity getUserEntity()
    {
        return userEntity;
    }

    public void setUserEntity(UserEntity userEntity)
    {
        this.userEntity = userEntity;
    }
}
