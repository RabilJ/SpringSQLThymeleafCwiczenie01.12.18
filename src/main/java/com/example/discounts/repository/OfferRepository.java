package com.example.discounts.repository;

import com.example.discounts.model.Offer;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;
import java.util.List;

@Repository
public class OfferRepository {

    @PersistenceContext
    private EntityManager entityManager;

    @Transactional
    public void save(Offer offer){
        entityManager.persist(offer);
    }
public List<Offer>findAll(){
        String jpql = "select o from Offer o";
    TypedQuery<Offer> query = entityManager.createQuery(jpql,Offer.class);
    return query.getResultList();
}
public Offer findById(Long id){
        return entityManager.find(Offer.class,id);
}
@Transactional
public void incrementViews(Long id){
        Offer offer = entityManager.find(Offer.class, id);
        Integer views = offer.getViews();
        offer.setViews(views+1);

}
}
