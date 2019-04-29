package com.foi.MyFinance.model;

import java.util.Date;

public class TransactionModel
{
    private float amount;

    private String details;

    private String toUser;

    private String fromUser;

    private String type;

    private Date date;

    private UserModel userModel;

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

    public UserModel getUserModel()
    {
        return userModel;
    }

    public void setUserModel(final UserModel userModel)
    {
        this.userModel = userModel;
    }
}
