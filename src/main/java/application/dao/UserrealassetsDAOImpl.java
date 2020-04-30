package application.dao;

import application.model.Userrealassets;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceContextType;

@Repository
public class UserrealassetsDAOImpl implements UserrealassetsDAO{

    @PersistenceContext(type = PersistenceContextType.EXTENDED)
    private EntityManager em;

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
