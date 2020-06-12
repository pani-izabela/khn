package application.dao;

import application.model.Address;
import application.model.AppUser;
import application.model.House;
import application.model.Plot;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceContextType;
import java.util.List;

@Repository
public class HouseDAOImpl implements HouseDAO{
    @PersistenceContext(type = PersistenceContextType.EXTENDED)
    private EntityManager em;

    @Override
    @Transactional(readOnly = true)
    public List<House> findAllHousesQuery() {
        try{
            return em.createNamedQuery(House.GET_HOUSES, House.class)
                    .getResultList();
        }
        catch (NoResultException e) {
            return null;
        }
    }

    @Override
    @Transactional(readOnly = true)
    public House findHouseByIdQuery(int id) {
        try{
            return em.createNamedQuery(House.GET_HOUSES_BY_ID, House.class)
                    .setParameter("id", id)
                    .getSingleResult();
        }
        catch (NoResultException e){
            return null;
        }
    }

    @Override
    @Transactional
    public House updateAppuser(House house, AppUser appUser) {
        try{
            house.setAppUser(appUser);
            return em.merge(house);

        } catch(NoResultException e){
            return null;
        }
    }

    @Override
    @Transactional
    public House addHouse(House house) {
        try {
            return em.merge(house);
        } catch (NoResultException e) {
            return null;
        }
    }

    @Override
    @Transactional(readOnly = true)
    public House findHouseByAddressId(Address addressId) {
        try{
            return em.createNamedQuery(House.GET_HOUSE_BY_ADDRESS_ID, House.class)
                    .setParameter("addressId", addressId)
                    .getSingleResult();
        }
        catch (NoResultException e){
            return null;
        }
    }
}
