package org.sales_management.service;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.sales_management.HibernateUtil;
import org.sales_management.entity.PriceVariationEntity;
import org.sales_management.entity.ProductEntity;
import org.sales_management.interfaces.CrudInterface;
import org.sales_management.repository.PriceVariationRepository;

import java.util.Collection;
import java.util.HashSet;

public class PriceVariationService implements CrudInterface<PriceVariationEntity> {
    private final PriceVariationRepository priceVariationRepository;

    public PriceVariationService() {
        this.priceVariationRepository = new PriceVariationRepository();
    }

    @Override
    public PriceVariationEntity create(PriceVariationEntity obj) {
        return null;
    }

    @Override
    public PriceVariationEntity getById(Long id) {
        Transaction transaction = null;
        PriceVariationEntity priceVariation = new PriceVariationEntity();
        try(Session session = HibernateUtil.getSessionFactory().getCurrentSession()) {
            transaction = session.beginTransaction();
            priceVariation = this.priceVariationRepository.getById(id);
            transaction.commit();
        }catch (Exception e){
            if (transaction!=null){
                transaction.rollback();
            }
            e.printStackTrace();
        }
        return priceVariation;
    }

    @Override
    public PriceVariationEntity deleteById(Long id) {
        Transaction transaction = null;
        PriceVariationEntity priceVariation = new PriceVariationEntity();
        try(Session session = HibernateUtil.getSessionFactory().getCurrentSession()) {
            transaction = session.beginTransaction();
            priceVariation = this.priceVariationRepository.deleteById(id);
            transaction.commit();
        }
        catch (Exception e){
            if (transaction!=null){
                transaction.rollback();
            }
        }
        return priceVariation;
    }

    @Override
    public PriceVariationEntity update(PriceVariationEntity new_priceVariation) {
        Transaction transaction = null;
        PriceVariationEntity priceVariation = new PriceVariationEntity();
        try(Session session = HibernateUtil.getSessionFactory().getCurrentSession()) {
            transaction = session.beginTransaction();
            priceVariation = this.priceVariationRepository.update(new_priceVariation);
            transaction.commit();
        }
        catch (Exception e){
            if (transaction!=null){
                transaction.rollback();
            }
        }
        return priceVariation;
    }

    @Override
    public Collection<PriceVariationEntity> getAll() {
        Transaction transaction = null;
        Collection<PriceVariationEntity> priceVariations = new HashSet<>();
        try(Session session = HibernateUtil.getSessionFactory().getCurrentSession()) {
            transaction = session.beginTransaction();
            priceVariations = this.priceVariationRepository.getAll();
            transaction.commit();
        }catch (Exception e){
            if (transaction!=null){
                transaction.rollback();
            }
            e.printStackTrace();
        }
        return priceVariations;
    }
}
