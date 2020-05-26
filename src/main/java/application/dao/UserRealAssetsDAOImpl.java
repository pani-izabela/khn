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
        try{
            return em.createNamedQuery(UserRealAssets.GET_USERREALASSETS, UserRealAssets.class)
                    .getResultList();
        }
        catch (NoResultException e) {
            return null;
        }
    }

    @Override
    @Transactional(readOnly = true)
    public UserRealAssets findUserrealAssetsByUserId(AppUser appUser) {
        try{
            return em.createNamedQuery(UserRealAssets.GET_USERREALASSETS_BY_APPUSERID, UserRealAssets.class)
                    .setParameter("appuserId", appUser)
                    .getSingleResult();

        }
        catch (NoResultException e) {
            return null;
        }
    }

    @Override
    @Transactional
    public UserRealAssets addUserrealassets(UserRealAssets userRealAssets) {
        try{
            return em.merge(userRealAssets);
        }
        catch (NoResultException e){
            return null;
        }
    }

    @Override
    @Transactional
    public UserRealAssets updateUserrealassetsHouse(UserRealAssets userRealAssets, House house) {
        try{
            userRealAssets.setHouse(house);
            return em.merge(userRealAssets);
        }
        catch (NoResultException e){
            return null;
        }
    }

    @Override
    @Transactional
    public UserRealAssets updateUserrealassetsPlot(UserRealAssets userRealAssets, Plot plot) {
        try{
            userRealAssets.setPlot(plot);
            return em.merge(userRealAssets);
        }
        catch (NoResultException e){
            return null;
        }
    }

    @Override
    @Transactional
    public UserRealAssets updateUserrealassetsFlat(UserRealAssets userRealAssets, Flat flat) {
        try{
            userRealAssets.setFlat(flat);
            return em.merge(userRealAssets);
        }
        catch (NoResultException e){
            return null;
        }
    }

}
