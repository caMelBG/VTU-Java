package residentevil.repository;

import residentevil.domain.entities.Capital;
import residentevil.domain.models.view.capital.CapitalSimpleViewModel;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.validation.annotation.Validated;

import java.util.List;

@Validated
@Repository
public interface CapitalRepository extends GenericRepository<Capital, Long> {

    @Query(value = "SELECT " +
            "NEW residentevil.domain.models.view.capital.CapitalSimpleViewModel(c.id, c.name) " +
            "FROM Capital AS c " +
            "ORDER BY c.name ASC")
    List<CapitalSimpleViewModel> findAllSimpleView();
}
