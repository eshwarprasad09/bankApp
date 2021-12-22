package com.bankapp.model;

import org.hibernate.annotations.CreationTimestamp;
import java.util.Date;
import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Table(name = "tranactionhistory")
public class TransactionHistory {

    public TransactionHistory() {
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @CreationTimestamp
    private Timestamp time;

    @Temporal(TemporalType.DATE)
    private  Date createdDate = new Date(System.currentTimeMillis());

    @Column(nullable = false, length = 45)
    private String fromAccount;

    @Column(nullable = false, length = 45)
    private String toAccount;

    @Column(nullable = false, length = 45)
    private Long amount;

    @Column(nullable=false,length=45)
    private String remark;

    @Column(name = "userId")
    private Long userId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Timestamp getTime() {
        return time;
    }

    public void setTime(Timestamp time) {
        this.time = time;
    }

    public String getFromAccount() {
        return fromAccount;
    }

    public void setFromAccount(String fromAccount) {
        this.fromAccount = fromAccount;
    }

    public String getToAccount() {
        return toAccount;
    }

    public void setToAccount(String toAccount) {
        this.toAccount = toAccount;
    }

    public Long getAmount() {
        return amount;
    }

    public void setAmount(Long amount) {
        this.amount = amount;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getRemark() { return remark;}

    public void setRemark(String remark) { this.remark = remark;}
}
