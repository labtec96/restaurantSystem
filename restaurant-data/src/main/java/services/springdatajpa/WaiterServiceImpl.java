package services.springdatajpa;

import javassist.tools.rmi.ObjectNotFoundException;
import lombok.extern.slf4j.Slf4j;
import model.Waiter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import repositories.WaiterRepository;
import services.WaiterService;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

/**
 * Created by ch on 2020-05-19
 */
@Slf4j
@Service
public class WaiterServiceImpl implements WaiterService {

    @Autowired
    WaiterRepository waiterRepository;

    @Override
    public Set<Waiter> findAll() {
        return new HashSet<>(waiterRepository.findAll());
    }

    @Override
    public Waiter findById(Long id) throws ObjectNotFoundException {
        Optional<Waiter> waiterOptional = waiterRepository.findById(id);

        if (!waiterOptional.isPresent()) {
            throw new error.ObjectNotFoundException("Waiter Not Found. For Id value " + id.toString());
        }

        return waiterOptional.get();
    }

    @Override
    public Waiter save(Waiter waiter) {
        return waiterRepository.save(waiter);
    }

    @Override
    public void delete(Waiter waiter) {
        waiterRepository.delete(waiter);
    }

    @Override
    public void deleteById(Long id) {
        waiterRepository.deleteById(id);
    }
}
