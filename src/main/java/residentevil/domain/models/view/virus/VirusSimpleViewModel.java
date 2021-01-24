package residentevil.domain.models.view.virus;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import residentevil.domain.api.Viewable;
import residentevil.domain.entities.Virus;
import residentevil.domain.enums.Magnitude;

import java.io.Serializable;
import java.util.Date;
import java.util.UUID;

@Getter
@EqualsAndHashCode(of = {"id"})
@NoArgsConstructor
@AllArgsConstructor
public final class VirusSimpleViewModel implements Viewable<Virus>, Serializable {

    private static final long serialVersionUID = 1L;

    private UUID id;

    private String name;

    private Magnitude magnitude;

    private Date releasedOn;
}
