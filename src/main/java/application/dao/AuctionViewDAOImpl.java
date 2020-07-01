package application.dao;

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
    @Transactional(readOnly = true)
    public List<AuctionView> findPropertyByAssetsTypeAndAppUserRole(String assetType, int appUserRole) {
        try{
            return em.createNamedQuery(AuctionView.GET_ALL_ASSETS_BY_TYPE_AND_APPUSERROLE, AuctionView.class)
                    .setParameter("assetType", assetType)
                    .setParameter("appuserRole", appUserRole)
                    .getResultList();
        }
        catch (NoResultException e){
            return null;
        }
    }

    @Override
    @Transactional(readOnly = true)
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
    @Transactional(readOnly = true)
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
    @Transactional(readOnly = true)
    public List<AuctionView> findPropertyByAppUserIdAndCustomerRole(int appUserId) {
        try{
            return em.createNamedQuery(AuctionView.GET_ALL_ASSETS_BY_APPUSER_ID_AND_APPUSERROLE, AuctionView.class)
                    .setParameter("appUserId", appUserId)
                    .setParameter("appUserRole", 1)
                    .getResultList();
        }
        catch (NoResultException e){
            return null;
        }
    }

    @Override
    @Transactional(readOnly = true)
    public List<AuctionView> findPropertyByAppUserIdAndSellerRole(int appUserId) {
        try{
            return em.createNamedQuery(AuctionView.GET_ALL_ASSETS_BY_APPUSER_ID_AND_APPUSERROLE, AuctionView.class)
                    .setParameter("appUserId", appUserId)
                    .setParameter("appUserRole", 2)
                    .getResultList();
        }
        catch (NoResultException e){
            return null;
        }
    }

}
