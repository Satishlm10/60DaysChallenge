package com._daysLearningChallenge.SpringSecurityBasics.Repository;

import java.util.List;

import com._daysLearningChallenge.SpringSecurityBasics.Model.AccountTransactions;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface AccountTransactionsRepository extends CrudRepository<AccountTransactions, Long> {

    List<AccountTransactions> findByCustomerIdOrderByTransactionDtDesc(int customerId);

}
