package org.sales_management.repository;

import org.hibernate.Session;
import org.sales_management.session.HibernateUtil;
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
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        PersonEntity personne = getById(id);
        if(personne!=null){ session.remove(personne); }
        return personne;
    }

    @Override
    public PersonEntity update(PersonEntity personne) {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        if (personne!=null){
            session.merge(personne);
        }
        return personne;
    }

    @Override
    public Collection<PersonEntity> getAll() {
        return null;
    }
}
