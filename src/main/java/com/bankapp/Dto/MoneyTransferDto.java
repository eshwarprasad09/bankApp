package com.bankapp.Dto;

public class MoneyTransferDto {

    private String fromAccount;
    private String toAccount;
    private Long amount;
    private String remark;

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

    public String getRemark() { return remark;}

    public void setRemark(String remark) { this.remark = remark;}
}
