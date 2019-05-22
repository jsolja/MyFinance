package com.foi.transactionservice.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Entity
@Table(name = "transaction")
public class TransactionEntity
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "transaction_id")
    private int id;

    @Column(name = "date")
    private Date date;

    @Column(name = "amount")
    private double amount;

    @Column(name = "details")
    private String details;

    @Column(name = "from_user")
    private String fromUser;

    @Column(name = "to_user")
    private String toUser;

    @Column(name = "type")
    private String type;

    @NotNull
    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name = "user_id", nullable = false)
    @JsonIgnore
    private UserEntity user;

    public TransactionEntity()
    {

    }

    public TransactionEntity(final TransactionEntity transactionEntity)
    {
        this.id = transactionEntity.getId();
        this.date = transactionEntity.getDate();
        this.amount = transactionEntity.getAmount();
        this.details = transactionEntity.details;
        this.fromUser = transactionEntity.getFromUser();
        this.toUser = transactionEntity.getToUser();
        this.type = transactionEntity.getType();
        this.user = transactionEntity.getUserEntity();
    }

    public int getId()
    {
        return id;
    }

    public void setId(final int id)
    {
        this.id = id;
    }

    public Date getDate()
    {
        return date;
    }

    public void setDate(final Date date)
    {
        this.date = date;
    }

    public double getAmount()
    {
        return amount;
    }

    public void setAmount(final double amount)
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

    public String getFromUser()
    {
        return fromUser;
    }

    public void setFromUser(final String fromUser)
    {
        this.fromUser = fromUser;
    }

    public String getToUser()
    {
        return toUser;
    }

    public void setToUser(final String toUser)
    {
        this.toUser = toUser;
    }

    public String getType()
    {
        return type;
    }

    public void setType(final String type)
    {
        this.type = type;
    }

    public UserEntity getUserEntity()
    {
        return user;
    }

    public void setUserEntity(final UserEntity userEntity)
    {
        this.user = userEntity;
    }
}
