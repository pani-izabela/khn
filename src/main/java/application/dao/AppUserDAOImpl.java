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
    @Transactional
    public List<AppUser> findAllQuery() {
        return em.createNamedQuery(AppUser.GET_APPUSERS, AppUser.class)
                .getResultList();
    }

    @Override
    @Transactional(readOnly = false)
    public AppUser addAppUser(AppUser appUser) {
        return em.merge(appUser);
    }


    @Override
    @Transactional
    public AppUser findById(int id) {
        return em.find(AppUser.class, id);
    }

    @Override
    public void deleteById(int id) {
        AppUser appUser = em.find(AppUser.class, id);
        if(appUser!=null)
            em.remove(appUser);
    }


    @Override
    @Transactional
    public AppUser findByEmailAndPassQuery(String email, String pass) {
        return em.createNamedQuery(AppUser.GET_APPUSER_BY_EMAIL_AND_PASS, AppUser.class)
                .setParameter("email",email)
                .setParameter("pass",pass)
                .getSingleResult();
    }

    @Override
    public AppUser findByEmail(String email) {
        return em.createNamedQuery(AppUser.GET_APPUSER_BY_EMAIL, AppUser.class)
                .setParameter("email", email)
                .getSingleResult();
    }
}
