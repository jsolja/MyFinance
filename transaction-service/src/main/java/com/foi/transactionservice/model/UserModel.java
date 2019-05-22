package com.foi.transactionservice.model;

public class UserModel
{
    private String username;
    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private String matchingPassword;
    private double balance;

    public String getUsername()
    {
        return username;
    }

    public void setUsername(final String username)
    {
        this.username = username;
    }

    public String getFirstName()
    {
        return firstName;
    }

    public void setFirstName(final String firstName)
    {
        this.firstName = firstName;
    }

    public String getLastName()
    {
        return lastName;
    }

    public void setLastName(final String lastName)
    {
        this.lastName = lastName;
    }

    public String getEmail()
    {
        return email;
    }

    public void setEmail(final String email)
    {
        this.email = email;
    }

    public String getPassword()
    {
        return password;
    }

    public void setPassword(final String password)
    {
        this.password = password;
    }

    public String getMatchingPassword()
    {
        return matchingPassword;
    }

    public void setMatchingPassword(final String matchingPassword)
    {
        this.matchingPassword = matchingPassword;
    }

    public double getBalance()
    {
        return balance;
    }

    public void setBalance(final double balance)
    {
        this.balance = balance;
    }
}
