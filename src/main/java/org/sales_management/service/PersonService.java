package org.sales_management.service;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.sales_management.HibernateUtil;
import org.sales_management.entity.PersonEntity;
import org.sales_management.interfaces.CrudInterface;
import org.sales_management.repository.PersonRepository;

import java.util.Collection;

public class PersonService implements CrudInterface<PersonEntity> {
    private final PersonRepository personRepository;

    public PersonService() {
        this.personRepository = new PersonRepository();
    }

    @Override
    public PersonEntity create(PersonEntity person) {
        Transaction transaction = null;
        try {
            Session session = HibernateUtil.getSessionFactory().getCurrentSession();
            transaction = session.beginTransaction();
            this.personRepository.create(person);
            transaction.commit();
        } catch (HibernateException e) {
            System.out.println("Error : error  while creating person");
            if (transaction!=null){
                transaction.rollback();
            }
        }
        return person;
    }

    @Override
    public PersonEntity getById(Long person_id) {
        Transaction transaction = null;
        try {
            Session session = HibernateUtil.getSessionFactory().getCurrentSession();
            transaction = session.beginTransaction();
            return this.personRepository.getById(person_id);
        } catch (HibernateException e) {
            System.out.println("Error : error  while finding person");
            if (transaction!=null){
                transaction.rollback();
            }
            return null;
        }
    }

    @Override
    public PersonEntity deleteById(Long id) {
        return null;
    }

    @Override
    public PersonEntity update(PersonEntity obj) {
        return null;
    }

    @Override
    public Collection<PersonEntity> getAll() {
        return null;
    }
}
