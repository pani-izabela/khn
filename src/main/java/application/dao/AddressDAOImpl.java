package application.dao;

import application.model.Address;
import application.model.AppUser;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceContextType;
import java.util.List;

@Repository
public class AddressDAOImpl implements AddressDAO {

    @PersistenceContext(type = PersistenceContextType.EXTENDED)
    private EntityManager em;

    @Override
    @Transactional
    public Address addAddress(Address address) {
        try {
            return em.merge(address);
        } catch (NoResultException e) {
            return null;
        }
    }

    @Override
    public Address findByCityAndStreetAndHouseNo(String city, String street, String houseNo) {
        try {
            return em.createNamedQuery(Address.GET_ADDRESS_BY_CITY_AND_STREET_AND_HOUSE_NO, Address.class)
                    .setParameter("city", city)
                    .setParameter("street", street)
                    .setParameter("houseNo", houseNo)
                    .getSingleResult();
        } catch (NoResultException e) {
            return null;
        }
    }

    @Override
    public List<Address> findAddressesByCityAndStreetAndHouseNo(String city, String street, String houseNo) {
        try {
            return em.createNamedQuery(Address.GET_ADDRESS_BY_CITY_AND_STREET_AND_HOUSE_NO, Address.class)
                    .setParameter("city", city)
                    .setParameter("street", street)
                    .setParameter("houseNo", houseNo)
                    .getResultList();
        } catch (NoResultException e) {
            return null;
        }
    }
}
