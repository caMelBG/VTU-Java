package residentevil.domain.entities;

import residentevil.domain.api.Identifiable;

abstract class BaseEntity<I> implements Identifiable<I> {

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }

        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        return getId() != null && getId().equals(((Identifiable) o).getId());
    }

    @Override
    public int hashCode() {
        return 31;
    }
}
