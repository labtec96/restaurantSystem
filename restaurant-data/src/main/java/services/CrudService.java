package services;

import javassist.tools.rmi.ObjectNotFoundException;

import java.util.Set;

/**
 * Created by ch on 2020-05-05
 */
public interface CrudService<T, ID>  {

        Set<T> findAll();

        T findById(ID id) throws ObjectNotFoundException;

        T save(T object);

        void delete(T object);

        void deleteById(ID id);
}
