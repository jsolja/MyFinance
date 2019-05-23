package com.foi.transactionservice.model;

import com.foi.transactionservice.entity.UserEntity;

/**
 * @author Jura Solja <jura.solja@ecx.io> ON 2019-05-23
 */
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
