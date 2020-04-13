package application.dao;

import application.model.House;
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
}
