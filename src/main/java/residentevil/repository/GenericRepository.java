package residentevil.repository;

import residentevil.domain.api.Viewable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.NotNull;
import java.util.Optional;

@Validated
@NoRepositoryBean
public interface GenericRepository<E, I> extends JpaRepository<E, I> {

    <T extends Viewable<E>> Optional<T> findProjectedById(@NotNull I id, @NotNull Class<T> projection);

}
