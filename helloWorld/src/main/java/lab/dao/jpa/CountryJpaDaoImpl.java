package lab.dao.jpa;

import lab.dao.CountryDao;
import lab.model.Country;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityTransaction;
import java.util.List;
import java.util.Optional;

@Repository
public class CountryJpaDaoImpl extends AbstractJpaDao implements CountryDao {

    @Override
    public void save(Country country) {
        Optional.ofNullable(emf.createEntityManager())
                .ifPresent(em -> {
                    EntityTransaction transaction = em.getTransaction();
                    transaction.begin();
                    try {
                        em.persist(country);
                    } finally {
                        transaction.commit();
                        em.close();
                    }

                });
    }

    @Override
    public List<Country> getAllCountries() {
        return Optional.ofNullable(emf.createEntityManager())
                .map(em -> {
//                    EntityTransaction transaction = em.getTransaction();
//                    transaction.begin();
                    try {
                        return em.createQuery(
                                "select c from Country c", Country.class)
                                .getResultList();
                    } finally {
//                        transaction.commit();
                        em.close();
                    }
                })
                .orElseThrow(() -> new RuntimeException("нету!"));
    }

    @Override
    public Country getCountryByName(String name) {
//		TODO: Implement it

        return null;
    }

}
