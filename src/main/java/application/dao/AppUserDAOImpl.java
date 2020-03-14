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
        try{
            return em.createNamedQuery(AppUser.GET_APPUSER_BY_ID, AppUser.class)
                    .setParameter("id", id)
                    .getSingleResult();
        }
        catch (NoResultException e){
            return null;
        }
    }

    @Override
    @Transactional
    public AppUser findById(int id) {
        try{
            return em.find(AppUser.class, id);
        }
        catch (NoResultException e){
            return null;
        }
    }

    @Override
    @Transactional
    public List<AppUser> findAllQuery() {
        try {
            return em.createNamedQuery(AppUser.GET_APPUSERS, AppUser.class)
                    .getResultList();
        }
        catch (NoResultException e){
            return null;
        }
    }

    @Override
    @Transactional(readOnly = false)
    public AppUser addAppUser(AppUser appUser) {
        try{
            return em.merge(appUser);
        }
        catch (NoResultException e){
            return null;
        }
    }

    @Override
    @Transactional // readOnly w wiekszosci metod
    public void deleteById(int id) {
        AppUser appUser = em.find(AppUser.class, id);
        if(appUser!=null)
            em.remove(appUser);
    }

    @Override
    @Transactional
    public AppUser findByEmailAndPassQuery(String email, String pass) {
        try{
            return em.createNamedQuery(AppUser.GET_APPUSER_BY_EMAIL_AND_PASS, AppUser.class)
                    .setParameter("email",email)
                    .setParameter("pass",pass)
                    .getSingleResult();
        } catch (NoResultException e){
            return null;
        }
    }

    @Override
    @Transactional
    public AppUser findByEmail(String email) {
    //public AppUser findByEmail(String jaszczurka) {
        try{
            return em.createNamedQuery(AppUser.GET_APPUSER_BY_EMAIL, AppUser.class)
                    .setParameter("email", email)
                    //.setParameter("wielblad", jaszczurka)
                    .getSingleResult();
        } catch(NoResultException e){
            return null;
        }
    }

    @Override
    @Transactional
    public AppUser updatePass(AppUser appUser, String newPass) {
        try{
            appUser.setPass(newPass);
            return em.merge(appUser);

        } catch(NoResultException e){
            return null;
        }
    }
}
