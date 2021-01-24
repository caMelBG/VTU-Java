package residentevil.domain.models.projections.virus;

import residentevil.domain.api.Viewable;
import residentevil.domain.entities.Virus;

import java.util.Date;

public interface VirusReleasedOnProjection extends Viewable<Virus> {

    Date getReleasedOn();
}
