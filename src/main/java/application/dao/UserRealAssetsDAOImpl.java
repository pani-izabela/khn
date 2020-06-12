package application.dao;

import application.model.*;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceContextType;
import java.util.List;

@Repository
public class UserRealAssetsDAOImpl implements UserRealAssetsDAO {

    @PersistenceContext(type = PersistenceContextType.EXTENDED)
    private EntityManager em;

    @Override
    @Transactional(readOnly = true)
    public List<UserRealAssets> findAllUserrealassetsQuery() {
        try {
            return em.createNamedQuery(UserRealAssets.GET_USERREALASSETS, UserRealAssets.class)
                    .getResultList();
        } catch (NoResultException e) {
            return null;
        }
    }

    @Override
    @Transactional(readOnly = true)
    public UserRealAssets findUserRealAssetsById(int id) {
        try {
            return em.find(UserRealAssets.class, id);
        } catch (NoResultException e) {
            return null;
        }

    }

    @Override
    @Transactional(readOnly = true)
    public UserRealAssets findUserrealAssetsByUserId(AppUser appUser) {
        try {
            return em.createNamedQuery(UserRealAssets.GET_USERREALASSETS_BY_APPUSERID, UserRealAssets.class)
                    .setParameter("appuserId", appUser)
                    .getSingleResult();

        } catch (NoResultException e) {
            return null;
        }
    }

    @Override
    public UserRealAssets findUserRealAssetsByHouseId(House house) {
        try{
            return em.createNamedQuery(UserRealAssets.GET_USERREALASSETS_BY_HOUSE, UserRealAssets.class)
                    .setParameter("houseId", house)
                    .getSingleResult();
        } catch (NoResultException e) {
            return null;
        }
    }

    @Override
    public UserRealAssets findUserRealAssetsByPlotId(Plot plot) {
        try{
            return em.createNamedQuery(UserRealAssets.GET_USERREALASSETS_BY_PLOT, UserRealAssets.class)
                    .setParameter("plotId", plot)
                    .getSingleResult();
        } catch (NoResultException e) {
            return null;
        }
    }

    @Override
    public UserRealAssets findUserRealAssetsByFlatId(Flat flat) {
        try{
            return em.createNamedQuery(UserRealAssets.GET_USERREALASSETS_BY_FLAT, UserRealAssets.class)
                    .setParameter("flatId", flat)
                    .getSingleResult();
        } catch (NoResultException e) {
            return null;
        }
    }

    @Override
    @Transactional
    public UserRealAssets addUserrealassets(UserRealAssets userRealAssets) {
        try {
            return em.merge(userRealAssets);
        } catch (NoResultException e) {
            return null;
        }
    }

    @Override
    @Transactional
    public UserRealAssets updateUserrealassetsHouse(UserRealAssets userRealAssets, House house) {
        try {
            userRealAssets.setHouse(house);
            userRealAssets.setAppUser(house.getAppUser());
            return em.merge(userRealAssets);
        } catch (NoResultException e) {
            return null;
        }
    }

    @Override
    @Transactional
    public UserRealAssets updateUserrealassetsPlot(UserRealAssets userRealAssets, Plot plot) {
        try {
            userRealAssets.setPlot(plot);
            userRealAssets.setAppUser(plot.getAppUser());
            return em.merge(userRealAssets);
        } catch (NoResultException e) {
            return null;
        }
    }

    @Override
    @Transactional
    public UserRealAssets updateUserrealassetsFlat(UserRealAssets userRealAssets, Flat flat) {
        try {
            userRealAssets.setFlat(flat);
            userRealAssets.setAppUser(flat.getAppUser());
            return em.merge(userRealAssets);
        } catch (NoResultException e) {
            return null;
        }
    }

}
