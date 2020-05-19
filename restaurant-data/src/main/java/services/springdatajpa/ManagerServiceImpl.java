package services.springdatajpa;

import dto.ManagerDto;
import error.UserAlreadyExistException;
import javassist.tools.rmi.ObjectNotFoundException;
import lombok.extern.slf4j.Slf4j;
import model.Manager;
import model.Waiter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import repositories.ManagerRepository;
import repositories.RoleRepository;
import services.ManagerService;
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
public class ManagerServiceImpl implements ManagerService {

    @Autowired
    ManagerRepository managerRepository;

    @Autowired
    UserService userService;

    @Autowired
    BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    RoleRepository roleRepository;

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

    @Override
    public Manager registerNewManagerAccount(ManagerDto managerDto) {
        log.info("Register new Waiter");
        if (userService.emailExists(managerDto.getEmail())) {
            System.out.println("Konto z takim eamilem juz istenieje");
            throw new UserAlreadyExistException("Istnieje już konto z takim adresem email: " + managerDto.getEmail());
        }
        Manager manager = new Manager();
        manager.setFirstName(managerDto.getFirstName());
        manager.setLastName(managerDto.getLastName());
        manager.setPassword(bCryptPasswordEncoder.encode(managerDto.getPassword()));
        manager.setEmail(managerDto.getEmail());
        manager.setSalary(managerDto.getSalary());
        manager.setAccountNumber(managerDto.getAccountNumber());
        manager.setNumberOfRestaurantRoom(managerDto.getNumberOfRestaurantRoom());
        manager.setRoles(Arrays.asList(roleRepository.findByName("ROLE_MANAGER")));

        return this.save(manager);
    }
}
