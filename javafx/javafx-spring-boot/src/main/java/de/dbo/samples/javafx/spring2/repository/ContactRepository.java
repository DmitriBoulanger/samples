package de.dbo.samples.javafx.spring2.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import de.dbo.samples.javafx.spring2.entity.Contact;

import java.util.List;

/**
 * Instance is generated as proxy by the Sring
 * 
 * @author Dmitri Boulanger, Hombach
 *
 * D. Knuth: Programs are meant to be read by humans and 
 *           only incidentally for computers to execute 
 *
 */
@Transactional(propagation = Propagation.MANDATORY)
public interface ContactRepository extends CrudRepository<Contact, Long> {

    List<Contact> findAll();

}
