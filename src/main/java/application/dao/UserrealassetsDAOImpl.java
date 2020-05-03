package application.dao;

import application.model.Userrealassets;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceContextType;
import java.util.List;

@Repository
public class UserrealassetsDAOImpl implements UserrealassetsDAO{

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
    @Transactional
    public Userrealassets addUserrealassets(Userrealassets userrealassets) {
        try{
            return em.merge(userrealassets);
        }
        catch (NoResultException e){
            return null;
        }
    }
}
