package residentevil.domain.models.view.capital;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import residentevil.domain.api.Viewable;
import residentevil.domain.entities.Capital;

import java.io.Serializable;

@Getter
@EqualsAndHashCode(of = {"id"})
@NoArgsConstructor
@AllArgsConstructor
public class CapitalSimpleViewModel implements Viewable<Capital>, Serializable {

    private static final long serialVersionUID = 1L;

    private Long id;

    private String name;
}
