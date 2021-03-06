package residentevil.service;

import residentevil.domain.entities.Virus;
import residentevil.domain.models.binding.virus.VirusBindingModel;
import residentevil.domain.models.view.virus.VirusSimpleViewModel;

import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface VirusService extends Service<Virus, UUID> {

    List<VirusSimpleViewModel> getViruses();

    void createVirus(@NotNull VirusBindingModel virus);

    Optional<VirusBindingModel> readVirus(@NotNull UUID id);

    void updateVirus(@NotNull VirusBindingModel virus);

    void deleteVirus(@NotNull UUID id);
}
