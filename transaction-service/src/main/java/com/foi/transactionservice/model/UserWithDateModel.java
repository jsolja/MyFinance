package com.foi.transactionservice.model;

import com.foi.transactionservice.entity.UserEntity;

/**
 * @author Jura Solja <jura.solja@ecx.io> ON 2019-05-23
 */
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
