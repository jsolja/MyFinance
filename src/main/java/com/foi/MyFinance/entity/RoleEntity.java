package com.foi.MyFinance.entity;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "role")
public class RoleEntity
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "role_id")
    private int id;

    @NotNull
    @Column(name = "role_description")
    private String roleDescription;

    public int getId()
    {
        return id;
    }

    public void setId(final int id)
    {
        this.id = id;
    }

    public String getRoleDescription()
    {
        return roleDescription;
    }

    public void setRoleDescription(final String roleDescription)
    {
        this.roleDescription = roleDescription;
    }
}