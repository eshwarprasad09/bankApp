package com.bankapp.Dto;

public class BalanceEnquiry {

    private String accountNumber;

    private Long balance;

    public String getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    public Long getBalance() {
        return balance;
    }

    public void setBalance(Long balance) {
        this.balance = balance;
    }

    public BalanceEnquiry(String accountNumber, Long balance) {
        super();
        this.accountNumber = accountNumber;
        this.balance = balance;
    }

    public BalanceEnquiry() {
    }
// Completed Balance Enquiry by prapulla
}
