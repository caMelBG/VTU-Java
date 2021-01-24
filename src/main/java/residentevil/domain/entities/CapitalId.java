package residentevil.domain.entities;

import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Table;
import java.io.Serializable;

@NoArgsConstructor
@Entity
@Table(name = "capitals")
public class CapitalId extends BaseLongEntity implements Serializable {

    private static final long serialVersionUID = 1L;
}
