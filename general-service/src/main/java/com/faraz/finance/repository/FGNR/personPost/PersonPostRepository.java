package com.faraz.finance.repository.FGNR.personPost;

import com.faraz.finance.model.FGNR.Person;
import com.faraz.finance.model.FGNR.PersonPost;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PersonPostRepository extends JpaRepository<PersonPost, Long> {
    Optional<PersonPost> findByPersonId(Person personId);


}
