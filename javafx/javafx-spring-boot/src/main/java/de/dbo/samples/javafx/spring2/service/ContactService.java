package de.dbo.samples.javafx.spring2.service;

import de.dbo.samples.javafx.spring2.entity.Contact;

import java.util.List;

/**
 * 
 * @author Dmitri Boulanger, Hombach
 *
 * D. Knuth: Programs are meant to be read by humans and 
 *           only incidentally for computers to execute 
 *
 */
public interface ContactService {

    public Contact save(Contact contact);

    public List<Contact> findAll();

}
