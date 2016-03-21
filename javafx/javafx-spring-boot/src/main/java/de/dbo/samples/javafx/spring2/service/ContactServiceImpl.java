package de.dbo.samples.javafx.spring2.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import javax.transaction.Transactional;

import de.dbo.samples.javafx.spring2.entity.Contact;
import de.dbo.samples.javafx.spring2.repository.ContactRepository;
import de.dbo.samples.javafx.spring2.ui.Controller;

import java.util.List;

/**
 * Date: 27.08.15
 * Time: 17:23
 *
 * @author Ruslan Molchanov (ruslanys@gmail.com)
 * @author http://mruslan.com
 */
@Service
@Transactional
public class ContactServiceImpl implements ContactService {
    private static final Logger log = LoggerFactory.getLogger(ContactServiceImpl.class);

    @Autowired
    private ContactRepository repository;

    /**
     * This method adds initial records into the database just after start
     * This is important since the database is in-memory one, 
     * and therefore it is empty
     */
    @PostConstruct
    public void generateTestData() {
	log.info("repository = ["+repository.getClass().getName()+"].");
	log.debug("initial test-data .....");
        save(new Contact("Ivan Ivanov",  "+123456789", "ivan@ivan.ov.ru"));
        save(new Contact("Peter Petrov", "+987654321", "petr@petr.ov.ru"));
        log.info("initial test-data has " + repository.count() + " entries");
    }

    @Override
    public Contact save(Contact contact) {
	log.debug("save .....");
	final Contact contactPersistent = repository.save(contact);
	log.debug("save done.");
        return contactPersistent;
    }

    @Override
    public List<Contact> findAll() {
        return repository.findAll();
    }
}
