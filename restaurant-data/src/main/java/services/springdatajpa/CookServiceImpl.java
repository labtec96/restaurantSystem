package services.springdatajpa;

import dto.CookDto;
import dto.ManagerDto;
import error.UserAlreadyExistException;
import javassist.tools.rmi.ObjectNotFoundException;
import lombok.extern.slf4j.Slf4j;
import model.Cook;
import model.Manager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import repositories.CookRepository;
import repositories.RoleRepository;
import services.CookService;
import services.UserService;

import java.util.Arrays;
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

    @Autowired
    UserService userService;

    @Autowired
    BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    RoleRepository roleRepository;

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


    @Override
    public Cook registerNewCookAccount(CookDto cookDto) {
        log.info("Register new Cook");
        if (userService.emailExists(cookDto.getEmail())) {
            System.out.println("Konto z takim eamilem juz istenieje");
            throw new UserAlreadyExistException("Istnieje już konto z takim adresem email: " + cookDto.getEmail());
        }
        Cook cook = new Cook();
        cook.setFirstName(cookDto.getFirstName());
        cook.setLastName(cookDto.getLastName());
        cook.setPassword(bCryptPasswordEncoder.encode(cookDto.getPassword()));
        cook.setEmail(cookDto.getEmail());
        cook.setSalary(cookDto.getSalary());
        cook.setAccountNumber(cookDto.getAccountNumber());
        cook.setNumberOfKitchen(cookDto.getNumberOfKitchen());
        cook.setRoles(Arrays.asList(roleRepository.findByName("ROLE_COOK")));

        return this.save(cook);
    }
}
