package org.sales_management.repository;

import org.hibernate.Session;
import org.sales_management.entity.ClientEntity;
import org.sales_management.interfaces.CrudInterface;
import org.sales_management.session.HibernateUtil;

import java.util.Collection;

public class ClientRepository implements CrudInterface<ClientEntity> {
    @Override
    public ClientEntity create(ClientEntity client) {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        session.persist(client);
        return client;
    }

    @Override
    public ClientEntity getById(Long id) {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        return session.get(ClientEntity.class,id);
    }

    @Override
    public ClientEntity deleteById(Long id) {
        return null;
    }

    @Override
    public ClientEntity update(ClientEntity obj) {
        return null;
    }

    @Override
    public Collection<ClientEntity> getAll() {
        return null;
    }
}
