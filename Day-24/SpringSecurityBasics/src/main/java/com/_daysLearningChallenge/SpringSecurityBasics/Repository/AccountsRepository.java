package com._daysLearningChallenge.SpringSecurityBasics.Repository;

import com._daysLearningChallenge.SpringSecurityBasics.Model.Accounts;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface AccountsRepository extends CrudRepository<Accounts, Long> {

    Accounts findByCustomerId(int customerId);

}