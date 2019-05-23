package com.foi.transactionservice.model;

import com.foi.transactionservice.entity.UserEntity;

import java.sql.Date;

public class TransactionModel
{
    private float amount;

    private String details;

    private String toUser;

    private String fromUser;

    private String type;

    private Date date;

    private UserEntity userEntity;

    public float getAmount()
    {
        return amount;
    }

    public void setAmount(final float amount)
    {
        this.amount = amount;
    }

    public String getDetails()
    {
        return details;
    }

    public void setDetails(final String details)
    {
        this.details = details;
    }

    public String getToUser()
    {
        return toUser;
    }

    public void setToUser(final String toUser)
    {
        this.toUser = toUser;
    }

    public String getFromUser()
    {
        return fromUser;
    }

    public void setFromUser(final String fromUser)
    {
        this.fromUser = fromUser;
    }

    public String getType()
    {
        return type;
    }

    public void setType(final String type)
    {
        this.type = type;
    }

    public Date getDate()
    {
        return date;
    }

    public void setDate(final Date date)
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
