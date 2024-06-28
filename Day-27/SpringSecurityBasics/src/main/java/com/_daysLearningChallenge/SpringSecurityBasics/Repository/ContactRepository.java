package com._daysLearningChallenge.SpringSecurityBasics.Repository;

import com._daysLearningChallenge.SpringSecurityBasics.Model.Contact;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;



@Repository
public interface ContactRepository extends CrudRepository<Contact, Long> {


}