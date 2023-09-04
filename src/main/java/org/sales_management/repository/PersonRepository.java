package org.sales_management.repository;

import org.hibernate.Session;
import org.sales_management.HibernateUtil;
import org.sales_management.entity.PersonEntity;
import org.sales_management.interfaces.CrudInterface;

import java.util.Collection;

public class PersonRepository implements CrudInterface<PersonEntity> {
    @Override
    public PersonEntity create(PersonEntity person) {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        session.persist(person);
        return person;
    }

    @Override
    public PersonEntity getById(Long id) {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        return session.get(PersonEntity.class,id);
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
