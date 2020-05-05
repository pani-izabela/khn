package application.dao;

import application.model.AppUser;
import application.model.Flat;
import application.model.Plot;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceContextType;
import java.util.List;

@Repository
public class PlotDAOImpl implements PlotDAO{
    @PersistenceContext(type = PersistenceContextType.EXTENDED)
    private EntityManager em;

    @Override
    @Transactional(readOnly = true)
    public List<Plot> findAllQuery() {
        try{
            return em.createNamedQuery(Plot.GET_PLOTS, Plot.class)
                    .getResultList();
        }
        catch (NoResultException e) {
            return null;
        }
    }

    @Override
    @Transactional
    public Plot findPlotByIdQuery(int id) {
        try{
            return em.createNamedQuery(Plot.GET_PLOTS_BY_ID, Plot.class)
                    .setParameter("id", id)
                    .getSingleResult();
        }
        catch (NoResultException e){
            return null;
        }
    }

    @Override
    @Transactional
    public Plot updateAppuser(Plot plot, AppUser appUser) {
        try{
            plot.setAppuser(appUser);
            return em.merge(plot);

        } catch(NoResultException e){
            return null;
        }
    }
}
