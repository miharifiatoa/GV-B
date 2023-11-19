package org.sales_management.service;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.sales_management.entity.ClientEntity;
import org.sales_management.interfaces.CrudInterface;
import org.sales_management.repository.ClientRepository;
import org.sales_management.session.HibernateUtil;

import java.util.Collection;

public class ClientService implements CrudInterface<ClientEntity> {
    private final ClientRepository clientRepository;

    public ClientService() {
        this.clientRepository = new ClientRepository();
    }

    @Override
    public ClientEntity create(ClientEntity client) {
        Transaction transaction = null;
        try(Session session = HibernateUtil.getSessionFactory().getCurrentSession()) {
            transaction = session.beginTransaction();
            this.clientRepository.create(client);
            transaction.commit();
        }catch (Exception e){
            if (transaction!=null){
                transaction.rollback();
            }
            e.printStackTrace();
        }
        return client;
    }

    @Override
    public ClientEntity getById(Long id) {
        Transaction transaction = null;
        ClientEntity client = new ClientEntity();
        try(Session session = HibernateUtil.getSessionFactory().getCurrentSession()) {
            transaction = session.beginTransaction();
            client = this.clientRepository.getById(id);
            transaction.commit();
        }catch (Exception e){
            if (transaction!=null){
                transaction.rollback();
            }
            e.printStackTrace();
        }
        return client;
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
    public ClientEntity getByPhone(String telephone) {
        try (Session session = HibernateUtil.getSessionFactory().getCurrentSession()) {
            Transaction transaction = session.beginTransaction();

            Query<ClientEntity> query = session.createQuery("FROM ClientEntity WHERE telephone = :telephone", ClientEntity.class);
            query.setParameter("telephone", telephone);

            ClientEntity result = query.uniqueResult();

            transaction.commit();
            return result;
        } catch (Exception e) {
            // Gérer les exceptions
            e.printStackTrace();
            return null;
        }
    }
}
