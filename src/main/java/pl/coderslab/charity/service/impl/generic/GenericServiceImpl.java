package pl.coderslab.charity.service.impl.generic;

import org.hibernate.service.spi.ServiceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import pl.coderslab.charity.entity.generic.GenericEntityID;
import pl.coderslab.charity.service.generic.GenericService;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

public abstract class GenericServiceImpl<T,R extends JpaRepository<T, Long>> implements GenericService<T> {

    protected R repository;

    @Autowired
    public GenericServiceImpl(R repository) {
        this.repository = repository;
    }

    @Override
    public T create(T entity) {
        return repository.save(entity);
    }

    @Override
    public T findById(Long id) {
        Optional<T> foundedOptional = repository.findById(id);
        if (foundedOptional.isPresent()) {
            return foundedOptional.get();
        }
        throw new ServiceException("Cannot find item with id " + id);
    }

    @Override
    public T update(T entity) {
        Optional<T> foundedOptional = repository.findById(((GenericEntityID) entity).getId());
        if (foundedOptional.isPresent()) {
            T foundedEntity = foundedOptional.get();
            ((GenericEntityID)entity).setId(((GenericEntityID) foundedEntity).getId());
            return repository.save(entity);
        }
        throw new ServiceException("Cannot find item to update");
    }

    @Override
    public Set<T> findAll() {
        return new HashSet<>(repository.findAll());
    }

    @Override
    public Set<T> findAll(int pageNo, int limit) {
        Pageable pageableRequest = PageRequest.of(pageNo, limit);
        return repository.findAll(pageableRequest).stream()
                .collect(Collectors.toSet());
    }

    @Override
    public boolean remove(T entity) {
        repository.findById(((GenericEntityID) entity).getId()).ifPresent(item -> repository.delete(entity));
        return true;
    }

    @Override
    public boolean removeById(Long id) {
        repository.findById(id)
                .ifPresent(item -> repository.delete(item));
        return true;
    }

}