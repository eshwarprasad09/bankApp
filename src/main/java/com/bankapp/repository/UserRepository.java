package com.bankapp.repository;

import com.bankapp.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    @Query("SELECT u.balance FROM User u WHERE u.accountNumber = ?1")
    public Long getBalance(String accountNo);

    @Query("SELECT u FROM User u WHERE u.accountNumber = ?1")
    public User getUserByAccountNo(String accountNo);

    @Query("SELECT u FROM User u WHERE u.email = ?1 and u.password = ?2")
    User getUserByLogin(String email, String password);

}
