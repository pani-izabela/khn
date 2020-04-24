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
}
