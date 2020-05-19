package services.springdatajpa;

import javassist.tools.rmi.ObjectNotFoundException;
import lombok.extern.slf4j.Slf4j;
import model.Cook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import repositories.CookRepository;
import services.CookService;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

/**
 * Created by ch on 2020-05-19
 */
@Slf4j
@Service
public class CookServiceImpl implements CookService {
    @Autowired
    CookRepository cookRepository;

    @Override
    public Set<Cook> findAll() {
        return new HashSet<>(cookRepository.findAll());
    }

    @Override
    public Cook findById(Long id) throws ObjectNotFoundException {
        Optional<Cook> cookOptional = cookRepository.findById(id);

        if (!cookOptional.isPresent()) {
            throw new error.ObjectNotFoundException("Cook Not Found. For Id value " + id.toString());
        }

        return cookOptional.get();
    }

    @Override
    public Cook save(Cook cook) {
        return cookRepository.save(cook);
    }

    @Override
    public void delete(Cook cook) {
        cookRepository.delete(cook);
    }

    @Override
    public void deleteById(Long id) {
        cookRepository.deleteById(id);
    }

}
