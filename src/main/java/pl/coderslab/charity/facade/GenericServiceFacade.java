package pl.coderslab.charity.facade;

import java.util.Collection;

public interface GenericServiceFacade<D> {

    D create(D dto);
    D update(D dto);
    Collection<D> findAll();
    Collection<D> findAll(int pageNo, int limit);
    boolean remove(D dto);
    boolean removeById(Long id);
    D findById(Long id);

}