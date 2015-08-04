package com.baselogic.tutorials.repositories.jpa;

import com.baselogic.bfl.domain.Incident;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IncidentJpaRepository extends JpaRepository<Incident, Long> {

    @Query(
            "Select t FROM Incident t WHERE LOWER(t.name) LIKE LOWER(CONCAT('%', :searchTerm, '%')) " +
                    "OR LOWER(t.baseNumber) LIKE LOWER(CONCAT('%', :searchTerm, '%'))"
    )
    public List<Incident> search(@Param("searchTerm") String searchTerm);



}

/*@Repository
public class IncidentJpaRepository extends AbstractJpaRepository<Incident> implements IncidentRepository {

    public IncidentJpaRepository() {
        super(Incident.class);
    }
}*/
