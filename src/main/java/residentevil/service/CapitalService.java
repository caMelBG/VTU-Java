package residentevil.service;

import residentevil.domain.entities.Capital;
import residentevil.domain.models.view.capital.CapitalSimpleViewModel;

import java.util.List;

public interface CapitalService extends Service<Capital, Long> {

    List<CapitalSimpleViewModel> getCapitals();
}
