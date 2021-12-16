package com.bankapp.service;

import com.bankapp.Dto.*;
import com.bankapp.model.AccountHistory;
import com.bankapp.model.User;
import com.bankapp.repository.AccountHistoryRepository;
import com.bankapp.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private AccountHistoryRepository accountHistoryRepository;

    public void saveUser(User user){
        userRepository.save(user);
    }

    public BalanceEnquiry getBalance(String accountNo) {
        BalanceEnquiry balance = new BalanceEnquiry(accountNo, 0L);
        balance.setBalance(userRepository.getBalance(accountNo));
        return balance;
    }

    public Boolean isAccountExists(String accountNo) {
        User user = userRepository.getUserByAccountNo(accountNo);
        if (user == null) {
            return false;
        }
        return true;
    }

    public void moneyTransfer(String toAccount, String fromAccount,MoneyTransferDto transferDto){

        if (isAccountExists(fromAccount)) {
            User fromUser = userRepository.getUserByAccountNo(fromAccount);
            fromUser.setBalance(fromUser.getBalance() - transferDto.getAmount());
            userRepository.save(fromUser);
        }

        if (isAccountExists(toAccount)) {
            User toUser = userRepository.getUserByAccountNo(toAccount);
            toUser.setBalance(toUser.getBalance() + transferDto.getAmount());
            userRepository.save(toUser);
        }

        User user = userRepository.getUserByAccountNo(fromAccount);
        AccountHistory accountHistory = new AccountHistory();
        accountHistory.setToAccount(transferDto.getToAccount());
        accountHistory.setFromAccount(transferDto.getFromAccount());
        accountHistory.setAmount(transferDto.getAmount());
        accountHistory.setUserId(user.getId());
        accountHistoryRepository.save(accountHistory);
    }

    public User getUserByAccountNo(String accountNo) {
        User user = userRepository.getUserByAccountNo(accountNo);
        return user;
    }

    public LoanStatus getLoanStatus(LoanDto loanDto){
        User user = userRepository.getUserByAccountNo(loanDto.getAccountNo());
        LoanStatus loanStatus = new LoanStatus();
        if (isAccountExists(loanDto.getAccountNo())) {
            loanStatus.setAccountNo(loanDto.getAccountNo());
            loanStatus.setLoanType(loanDto.getLoanType());
            loanStatus.setAmount(loanDto.getAmount());
            if(user.getBalance() > 600){
                loanStatus.setLoanStatus("Approved");
            }
            else{
                loanStatus.setLoanStatus("Not Approved");
            }
            return loanStatus;
        }
        loanStatus.setLoanStatus("Not approved Account Not Exists");
        return loanStatus;
    }

    public User getLogin(LoginDto loginDto){
        User user = userRepository.getUserByLogin(loginDto.getEmail(), loginDto.getPassword());
        return user;
    }
}
