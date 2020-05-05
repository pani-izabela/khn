package application.dao;

import application.model.AppUser;
import application.model.AuctionView;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceContextType;
import java.util.ArrayList;
import java.util.List;

@Repository
public class AuctionViewDAOImpl implements AuctionViewDAO {

    @PersistenceContext(type = PersistenceContextType.EXTENDED)
    private EntityManager em;

    @Override
    @Transactional(readOnly = true)
    public List<AuctionView> findAllQuery() {
        try {
            return em.createNamedQuery(AuctionView.GET_ALL, AuctionView.class)
                    .getResultList();
        }
        catch (NoResultException e){
            return null;
        }
    }

    @Override
    @Transactional
    public List<AuctionView> findAllQueryByAssetsType(String assetType) {
        try{
            return em.createNamedQuery(AuctionView.GET_ALL_Assets, AuctionView.class)
                    .setParameter("assetType", assetType)
                    .getResultList();
        }
        catch (NoResultException e){
            return null;
        }
    }

    @Override
    @Transactional
    public List<AuctionView> findPropertyByAssetsTypeAndAppuserRole(String assetType, int appuserRole) {
        try{
            return em.createNamedQuery(AuctionView.GET_ALL_ASSETS_BY_TYPE_AND_APPUSERROLE, AuctionView.class)
                    .setParameter("assetType", assetType)
                    .setParameter("appuserRole", appuserRole)
                    .getResultList();
        }
        catch (NoResultException e){
            return null;
        }
    }

    @Override
    @Transactional
    public AuctionView findByAssetsTypeAndAssetId(String assetType, int assetId) {
        try{
            return em.createNamedQuery(AuctionView.GET_ALL_ASSETS_BY_TYPE_AND_ASSETID, AuctionView.class)
                    .setParameter("assetType", assetType)
                    .setParameter("assetId", assetId)
                    .getSingleResult();
        }
        catch (NoResultException e){
            return null;
        }
    }

    @Override
    @Transactional
    public List<AuctionView> findByAdress(String city, String postcode, String homenumber) {
        try{
            return em.createNamedQuery(AuctionView.GET_ALL_ASSETS_BY_ADRESS, AuctionView.class)
                    .setParameter("city", city)
                    .setParameter("postcode", postcode)
                    .setParameter("homenumber", homenumber)
                    .getResultList();
        }
        catch (NoResultException e){
            return null;
        }
    }

    @Override
    @Transactional
    public void deleteById(int id) {
        AuctionView auctionView = em.find(AuctionView.class,id);
        if(auctionView!=null)
            em.remove(auctionView);
    }
}
