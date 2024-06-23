package com._daysLearningChallenge.SpringSecurityBasics.Repository;

import java.util.List;

import com._daysLearningChallenge.SpringSecurityBasics.Model.Loans;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;



@Repository
public interface LoansRepository extends CrudRepository<Loans, Long> {

    List<Loans> findByCustomerIdOrderByStartDtDesc(int customerId);

}