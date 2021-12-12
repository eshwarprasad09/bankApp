package com.bankapp.repository;

import com.bankapp.model.AccountHistory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AccountHistoryRepository extends JpaRepository<AccountHistory, Long> {

    @Query(value = "select * from accounthistory ah where ah.from_account = ?1 or ah.to_account = ?1",nativeQuery = true)
    List<AccountHistory> getMiniStatement(@Param("fromAccount") String fromAccount);
}
