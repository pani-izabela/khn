package application.dao;

import application.model.AppUser;
import application.model.Finance;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceContextType;

@Repository
public class FinanceDAOImpl implements FinanceDAO {

    @PersistenceContext(type = PersistenceContextType.EXTENDED)
    private EntityManager em;

    @Override
    @Transactional(readOnly = true)
    public Finance findByAppuseridQuery(AppUser appUserId) {
        try {
            return em.createNamedQuery(Finance.GET_FINANCE_BY_APPUSERID, Finance.class)
                    .setParameter("appuserId", appUserId)
                    .getSingleResult();
        }
        catch (NoResultException e){
            return null;
        }
    }

    @Override
    @Transactional
    public Finance updateAmount(AppUser oldAppUserId, double  newAmount) {
        try{
            Finance finance = findByAppuseridQuery(oldAppUserId);
            finance.setAmount(newAmount);
            return em.merge(finance);

        } catch(NoResultException e){
            return null;
        }
    }

    @Override
    @Transactional(readOnly = true)
    public Finance findById(int financeId) {
        try{
            return em.createNamedQuery(Finance.GET_FINANCE_BY_ID, Finance.class)
                    .setParameter("financeId", financeId)
                    .getSingleResult();
        }
        catch (NoResultException e){
            return null;
        }
    }
}
