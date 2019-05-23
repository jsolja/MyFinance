package com.foi.userservice.model;

import com.foi.userservice.entity.UserEntity;

public class UserWithYearModel
{
    private String year;
    private UserEntity userEntity;

    public String getYear()
    {
        return year;
    }

    public void setYear(String year)
    {
        this.year = year;
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
