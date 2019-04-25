package com.foi.MyFinance.model;

public class ResetPasswordModel
{
    private String password;
    private String token;

    public String getToken()
    {
        return token;
    }

    public void setToken(final String token)
    {
        this.token = token;
    }

    public String getPassword()
    {
        return password;
    }

    public void setPassword(final String password)
    {
        this.password = password;
    }
}
