package org.sales_management.repository;

import org.hibernate.Session;
import org.hibernate.query.Query;
import org.sales_management.HibernateUtil;
import org.sales_management.entity.PriceVariationEntity;
import org.sales_management.interfaces.CrudInterface;
import org.sales_management.service.PriceVariationService;

import java.util.Collection;

public class PriceVariationRepository implements CrudInterface<PriceVariationEntity> {
    @Override
    public PriceVariationEntity create(PriceVariationEntity obj) {
        return null;
    }

    @Override
    public PriceVariationEntity getById(Long id) {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        return session.get(PriceVariationEntity.class,id);
    }

    @Override
    public PriceVariationEntity deleteById(Long id) {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        PriceVariationEntity priceVariation = this.getById(id);
        if (priceVariation!=null){
            session.remove(priceVariation);
        }
        return priceVariation;
    }

    @Override
    public PriceVariationEntity update(PriceVariationEntity priceVariation) {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        if (priceVariation != null){
            session.merge(priceVariation);
        }
        return priceVariation;
    }

    @Override
    public Collection<PriceVariationEntity> getAll() {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        return session.createQuery("from PriceVariationEntity",PriceVariationEntity.class).getResultList();
    }
}
