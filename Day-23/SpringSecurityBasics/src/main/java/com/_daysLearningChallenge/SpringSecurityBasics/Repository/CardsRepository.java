package com._daysLearningChallenge.SpringSecurityBasics.Repository;

import java.util.List;

import com._daysLearningChallenge.SpringSecurityBasics.Model.Cards;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;



@Repository
public interface CardsRepository extends CrudRepository<Cards, Long> {

    List<Cards> findByCustomerId(int customerId);

}