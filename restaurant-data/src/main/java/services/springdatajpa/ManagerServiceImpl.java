package services.springdatajpa;

import javassist.tools.rmi.ObjectNotFoundException;
import lombok.extern.slf4j.Slf4j;
import model.Manager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import repositories.ManagerRepository;
import services.ManagerService;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

/**
 * Created by ch on 2020-05-19
 */
@Slf4j
@Service
public class ManagerServiceImpl implements ManagerService {

    @Autowired
    ManagerRepository managerRepository;

    @Override
    public Set<Manager> findAll() {
        return new HashSet<>(managerRepository.findAll());
    }

    @Override
    public Manager findById(Long id) throws ObjectNotFoundException {
        Optional<Manager> managerOptional = managerRepository.findById(id);

        if (!managerOptional.isPresent()) {
            throw new error.ObjectNotFoundException("Manager Not Found. For Id value " + id.toString());
        }

        return managerOptional.get();
    }

    @Override
    public Manager save(Manager manager) {
        return managerRepository.save(manager);
    }

    @Override
    public void delete(Manager manager) {
        managerRepository.delete(manager);
    }

    @Override
    public void deleteById(Long id) {
        managerRepository.deleteById(id);
    }

}
