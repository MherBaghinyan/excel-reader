package com.university.repository;

import com.university.entity.Person;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by Mher on 12/22/2015.
 */
@Repository
public interface PersonRepository extends CrudRepository<Person, Long> {
}
