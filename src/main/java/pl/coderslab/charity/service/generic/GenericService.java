package pl.coderslab.charity.service.generic;

import java.util.Set;

public interface GenericService<T> {

    T create(T entity);
    T update(T entity);
    Set<T> findAll();
    Set<T> findAll(int pageNo, int limit);
    boolean remove(T entity);
    boolean removeById(Long id);
    T findById(Long id);

}