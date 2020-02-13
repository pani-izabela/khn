package application.dao;

import application.model.AppUser;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceContextType;
import java.util.List;

@Repository
public class AppUserDAOImpl implements AppUserDAO {

    @PersistenceContext(type = PersistenceContextType.EXTENDED)
    private EntityManager em;


    @Override
    public AppUser findByIdQuery(int id) {
        return em.createNamedQuery(AppUser.GET_APPUSER_BY_ID, AppUser.class)
                .setParameter("id", id)
                .getSingleResult();
    }

    @Override
    public List<AppUser> findAllQuery() {
        return em.createNamedQuery(AppUser.GET_APPUSERS, AppUser.class)
                .getResultList();
    }

    @Override
    @Transactional(readOnly = false)
    public AppUser addAppUser(AppUser appUser) {
        return em.merge(appUser);
    }
}
