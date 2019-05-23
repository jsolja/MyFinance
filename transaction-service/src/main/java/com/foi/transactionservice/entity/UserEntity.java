package com.foi.transactionservice.entity;

import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "user")
public class UserEntity
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private int id;

    @NotNull
    @Column(name = "email", unique = true)
    private String email;

    @NotNull
    @Column(name = "password")
    private String password;

    @NotNull
    @Column(name = "username", unique = true)
    private String username;

    @NotNull
    @Column(name = "first_name")
    private String firstName;

    @NotNull
    @Column(name = "last_name")
    private String lastName;

    @ColumnDefault("0.0")
    @Column(name = "balance")
    private double balance;

    @ColumnDefault("1")
    @Column(name = "active")
    private boolean active;

    @Column(name = "token")
    private String token;

    @NotNull
    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @OnDelete(action = OnDeleteAction.NO_ACTION)
    @JoinColumn(name = "role_id")
    private RoleEntity role;

    public UserEntity()
    {

    }

    public UserEntity(final UserEntity user)
    {
        this.active = user.isActive();
        this.email = user.getEmail();
        this.role = user.getRole();
        this.username = user.getUsername();
        this.firstName = user.getFirstName();
        this.lastName = user.getLastName();
        this.id = user.getId();
        this.password = user.getPassword();
        this.balance = user.getBalance();
    }

    public String getPassword()
    {
        return this.password;
    }

    public void setPassword(final String password)
    {
        this.password = password;
    }

    public String getUsername()
    {
        return this.username;
    }

    public boolean isAccountNonExpired()
    {
        return true;
    }

    public boolean isAccountNonLocked()
    {
        return true;
    }

    public boolean isCredentialsNonExpired()
    {
        return true;
    }

    public boolean isEnabled()
    {
        return this.active;
    }

    public double getBalance()
    {
        return this.balance;
    }

    public void setBalance(final float balance)
    {
        this.balance = balance;
    }

    public int getId()
    {
        return this.id;
    }

    public void setId(final int id)
    {
        this.id = id;
    }

    public String getEmail()
    {
        return this.email;
    }

    public void setEmail(final String email)
    {
        this.email = email;
    }

    public void setUsername(final String username)
    {
        this.username = username;
    }

    public String getFirstName()
    {
        return this.firstName;
    }

    public void setFirstName(final String firstName)
    {
        this.firstName = firstName;
    }

    public String getLastName()
    {
        return this.lastName;
    }

    public void setLastName(final String lastName)
    {
        this.lastName = lastName;
    }

    public boolean isActive()
    {
        return this.active;
    }

    public void setActive(final boolean active)
    {
        this.active = active;
    }

    public RoleEntity getRole()
    {
        return this.role;
    }

    public void setRole(final RoleEntity role)
    {
        this.role = role;
    }

    public String getToken()
    {
        return this.token;
    }

    public void setToken(final String token)
    {
        this.token = token;
    }
}