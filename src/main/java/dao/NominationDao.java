package dao;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.PersistenceContext;
import model.Nomination;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
public class NominationDao {

    @PersistenceContext(unitName = "entityManagerFactory")
    private EntityManager emf;

    @Transactional
    public void saveNomination(Nomination nomination) {
        emf.persist(nomination);
    }

}
