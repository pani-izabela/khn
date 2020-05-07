package application.dao;

import application.model.AppUser;
import application.model.Flat;
import application.model.House;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceContextType;
import java.util.List;

@Repository
public class FlatDAOImpl implements FlatDAO{
    @PersistenceContext(type = PersistenceContextType.EXTENDED)
    private EntityManager em;

    @Override
    @Transactional(readOnly = true)
    public List<Flat> findAllQuery() {
        try{
            return em.createNamedQuery(Flat.GET_FLATS, Flat.class)
                    .getResultList();
        }
        catch (NoResultException e) {
            return null;
        }
    }

    @Override
    @Transactional(readOnly = true)
    public Flat findFlatByIdQuery(int id) {
        try{
            return em.createNamedQuery(Flat.GET_FLATS_BY_ID, Flat.class)
                    .setParameter("id", id)
                    .getSingleResult();
        }
        catch (NoResultException e){
            return null;
        }
    }

    @Override
    @Transactional
    public Flat updateAppuser(Flat flat, AppUser appUser) {
        try{
            flat.setAppuser(appUser);
            return em.merge(flat);

        } catch(NoResultException e){
            return null;
        }
    }
}
