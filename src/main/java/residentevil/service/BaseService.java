package residentevil.service;

import org.modelmapper.ModelMapper;
import residentevil.domain.api.Bindable;
import residentevil.domain.api.Identifiable;
import residentevil.domain.api.Viewable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.lang.reflect.ParameterizedType;
import java.util.List;
import java.util.Optional;
import java.util.logging.Logger;
import java.util.stream.Collectors;

@Validated
@Transactional
abstract class BaseService<E extends Identifiable<I>, I, R extends JpaRepository<E, I>> implements Service<E, I> {

    protected final R repository;
    protected final ModelMapper mapper;
    private final Class<E> entityClass;

    protected BaseService(R repository,
                          ModelMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
        entityClass = initEntityClass();
    }

    protected abstract Logger logger();

    @Override
    public <B extends Bindable<E>> void create(@NotNull @Valid B bindingModel) {
        createAndGet(bindingModel);
    }

    @Override
    public <B extends Bindable<E>, V extends Viewable<E>>
    V createAndGet(@NotNull @Valid B bindingModel, @NotNull Class<V> viewModelClass) {
        E entity = createAndGet(bindingModel);
        return mapper.map(entity, viewModelClass);
    }

    @Override
    @Transactional(readOnly = true)
    public <V extends Viewable<E>> Optional<V> findById(@NotNull I id, @NotNull Class<V> viewModelClass) {
        return repository
                .findById(id)
                .map(e -> mapper.map(e, viewModelClass));
    }

    @Override
    @Transactional(readOnly = true)
    public <V extends Viewable<E>> List<V> findAll(@NotNull Class<V> viewModelClass) {
        return repository
                .findAll()
                .stream()
                .map(t -> mapper.map(t, viewModelClass))
                .collect(Collectors.toList());
    }

    @Override
    public boolean deleteById(@NotNull I id) {
        return deleteByIdAndGet(id)
                .isPresent();
    }

    @Override
    public <V extends Viewable<E>> Optional<V> deleteByIdAndGet(@NotNull I id, @NotNull Class<V> viewModelClass) {
        return deleteByIdAndGet(id)
                .map(e -> mapper.map(e, viewModelClass));
    }

    private <B extends Bindable<E>> E createAndGet(B bindingModel) {
        return repository.save(mapper.map(bindingModel, entityClass));
    }

    private Optional<E> deleteByIdAndGet(I id) {
        return repository
                .findById(id)
                .map(e -> {
                    repository.delete(e);
                    return e;
                });
    }

    @SuppressWarnings("unchecked")
    private Class<E> initEntityClass() {
        return (Class<E>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];
    }
}
