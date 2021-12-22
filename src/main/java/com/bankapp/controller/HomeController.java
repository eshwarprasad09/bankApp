package com.bankapp.controller;

import com.bankapp.Dto.*;
import com.bankapp.model.TransactionHistory;
import com.bankapp.model.Role;
import com.bankapp.model.User;
import com.bankapp.repository.TransactionHistoryRepository;
import com.bankapp.repository.RoleRepository;
import com.bankapp.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;


@RestController
public class HomeController {

    @Autowired
    private UserService userService;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private TransactionHistoryRepository transactionHistoryRepository;

    @GetMapping("/")
    public String home() {
        return "Welcome to HSBC Bank";
    }

    @PostMapping("/openaccount")
    public ResponseEntity<User> openAccount(@RequestBody UserDto userDto) {
        User user = new User();
        user.setName(userDto.getName());
        user.setEmail(userDto.getEmail());
        user.setPassword(userDto.getPassword());
        user.setBalance(1000L);
        user.setAccountNumber("1");

        Role roleUser = roleRepository.findByName("user");
        user.addRole(roleUser);

        userService.saveUser(user);
        return new ResponseEntity<User>(user, HttpStatus.CREATED);
    }


    @GetMapping("/getbalance/{accountNo}")
    public ResponseEntity<BalanceEnquiry> getBalance(@PathVariable("accountNo") String accountNo) {
        if (!userService.isAccountExists(accountNo)) {
            return new ResponseEntity<BalanceEnquiry>(HttpStatus.BAD_REQUEST);
        }
        BalanceEnquiry balance = userService.getBalance(accountNo);
        return new ResponseEntity<BalanceEnquiry>(balance, HttpStatus.ACCEPTED);
    }
    @PostMapping("/moneytransfer")
    public String moneyTransfer(@RequestBody MoneyTransferDto moneyTransferDto) {
        String toAccount = moneyTransferDto.getToAccount();
        String fromAccount = moneyTransferDto.getFromAccount();
        String remark = moneyTransferDto.getRemark();
        userService.moneyTransfer(toAccount, fromAccount, moneyTransferDto);
        return "Transfer Success";
    }

    @GetMapping("/getuser/{accountNo}")
    public ResponseEntity<User> getUser(@PathVariable("accountNo") String accountNo) {
        User user = userService.getUserByAccountNo(accountNo);
        return new ResponseEntity<User>(user, HttpStatus.FOUND);
    }

    @GetMapping("/transactionhistory/{accountNo}")
    public ResponseEntity<List> getTransactionHistory(@PathVariable("accountNo") String accountNo) {
        List transactionHistoryList = transactionHistoryRepository.getMiniStatement(accountNo);
//        accountHistoryList = accountHistoryRepository.getMiniStatement(accountNo);
        User user = userService.getUserByAccountNo(accountNo);
        UserAccount userAccount = new UserAccount();
        userAccount.setAccountNo(user.getAccountNumber());
        userAccount.setName(user.getName());
        userAccount.setBalance(user.getBalance());
        transactionHistoryList.add(0, userAccount);
        return new ResponseEntity<List>(transactionHistoryList, HttpStatus.FOUND);
    }

    @GetMapping("/loan")
    public ResponseEntity<LoanStatus> loanStatus(@RequestBody LoanDto loanDto) {
        User user = userService.getUserByAccountNo(loanDto.getAccountNo());
        LoanStatus loanStatus = userService.getLoanStatus(loanDto);
        return new ResponseEntity<LoanStatus>(loanStatus, HttpStatus.OK);
    }

    @GetMapping("/login")
    public String login(@RequestBody LoginDto loginDto) {
        User user = userService.getLogin(loginDto);
        if (user != null) {
            Set<Role> roles = user.getRoles();
            for (Role role : roles) {
                return role.getName() + " Login Success";
            }
            return "Login Success";
        } else {
            return "Invalid user";
        }


    }
    //junit testing
}











