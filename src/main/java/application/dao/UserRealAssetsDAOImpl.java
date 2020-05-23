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
    public List<Userrealassets> findAllUserrealassetsQuery() {
        try{
            return em.createNamedQuery(Userrealassets.GET_USERREALASSETS, Userrealassets.class)
                    .getResultList();
        }
        catch (NoResultException e) {
            return null;
        }
    }

    @Override
    @Transactional(readOnly = true)
    public Userrealassets findUserrealAssetsByUserId(AppUser appUser) {
        try{
            return em.createNamedQuery(Userrealassets.GET_USERREALASSETS_BY_APPUSERID, Userrealassets.class)
                    .setParameter("appuserId", appUser)
                    .getSingleResult();

        }
        catch (NoResultException e) {
            return null;
        }
    }

    @Override
    @Transactional
    public Userrealassets addUserrealassets(Userrealassets userrealassets) {
        try{
            return em.merge(userrealassets);
        }
        catch (NoResultException e){
            return null;
        }
    }

    @Override
    @Transactional
    public Userrealassets updateUserrealassetsHouse(Userrealassets userrealassets, House house) {
        try{
            userrealassets.setHouse(house);
            return em.merge(userrealassets);
        }
        catch (NoResultException e){
            return null;
        }
    }

    @Override
    @Transactional
    public Userrealassets updateUserrealassetsPlot(Userrealassets userrealassets, Plot plot) {
        try{
            userrealassets.setPlot(plot);
            return em.merge(userrealassets);
        }
        catch (NoResultException e){
            return null;
        }
    }

    @Override
    @Transactional
    public Userrealassets updateUserrealassetsFlat(Userrealassets userrealassets, Flat flat) {
        try{
            userrealassets.setFlat(flat);
            return em.merge(userrealassets);
        }
        catch (NoResultException e){
            return null;
        }
    }

}
