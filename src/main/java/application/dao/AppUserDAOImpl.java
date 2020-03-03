package application.dao;

import application.model.AppUser;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceContextType;
import java.util.List;

@Repository
public class AppUserDAOImpl implements AppUserDAO {

    @PersistenceContext(type = PersistenceContextType.EXTENDED)
    private EntityManager em;


    @Override
    @Transactional
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
    @Transactional
    public void deleteById(int id) {
        AppUser appUser = em.find(AppUser.class, id);
        if(appUser!=null)
            em.remove(appUser);
    }

    @Override
    @Transactional
    public AppUser findByEmailAndPassQuery(String email, String pass) {
        AppUser appUser;
        try{
            appUser = em.createNamedQuery(AppUser.GET_APPUSER_BY_EMAIL_AND_PASS, AppUser.class)
                    .setParameter("email",email)
                    .setParameter("pass",pass)
                    .getSingleResult();
        } catch (NoResultException e){
            appUser = null;
        }
        return appUser;
    }

    @Override
    @Transactional
    public AppUser findByEmail(String email) {
        AppUser appUser;
        try{
            appUser = em.createNamedQuery(AppUser.GET_APPUSER_BY_EMAIL, AppUser.class)
                    .setParameter("email", email)
                    .getSingleResult();
        } catch(NoResultException e){
            appUser = null;
        }

        return appUser;
    }

    @Override
    @Transactional
    public AppUser updatePass(AppUser appUser, String newPass) {
        AppUser appUserToReturn;
        try{
            appUser.setPass(newPass);
            appUserToReturn = em.merge(appUser);

        } catch(NoResultException e){
            appUserToReturn = null;
        }
        return appUserToReturn;
    }
}
