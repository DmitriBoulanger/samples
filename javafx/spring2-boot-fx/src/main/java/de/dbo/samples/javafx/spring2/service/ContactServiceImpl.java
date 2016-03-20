package de.dbo.samples.javafx.spring2.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import javax.transaction.Transactional;

import de.dbo.samples.javafx.spring2.entity.Contact;
import de.dbo.samples.javafx.spring2.repository.ContactRepository;

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

    @Autowired
    private ContactRepository repository;

    /**
     * Метод добавляет парочку записей в БД после запуска приложения,
     * чтобы не было совсем пусто.
     *
     * Из-за того, что подключена H2 (in-memory) БД.
     */
    @PostConstruct
    public void generateTestData() {
        save(new Contact("Иван Иванов", "+123456789", "ivan@ivan.ov"));
        save(new Contact("Петр Петров", "+987654321", "petr@pe.tr"));
    }

    @Override
    public Contact save(Contact contact) {
        return repository.save(contact);
    }

    @Override
    public List<Contact> findAll() {
        return repository.findAll();
    }
}
