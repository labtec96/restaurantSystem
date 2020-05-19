package services.springdatajpa;

import dto.WaiterDto;
import error.UserAlreadyExistException;
import javassist.tools.rmi.ObjectNotFoundException;
import lombok.extern.slf4j.Slf4j;
import model.Waiter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import repositories.RoleRepository;
import repositories.WaiterRepository;
import services.WaiterService;

import java.util.Arrays;
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

    @Autowired
    RoleRepository roleRepository;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

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

    @Override
    public Waiter registerNewWaiterAccount(WaiterDto waiterDto) {
        log.info("Register new Waiter");

        Waiter waiter = new Waiter();
        waiter.setFirstName(waiterDto.getFirstName());
        waiter.setLastName(waiterDto.getLastName());
        waiter.setPassword(bCryptPasswordEncoder.encode(waiterDto.getPassword()));
        waiter.setEmail(waiterDto.getEmail());
        waiter.setSalary(waiterDto.getSalary());
        waiter.setAccountNumber(waiterDto.getAccountNumber());
        waiter.setPercentageOfTips(waiterDto.getPercentageOfTips());
        waiter.setRoles(Arrays.asList(roleRepository.findByName("ROLE_WAITER")));

        return this.save(waiter);
    }
}
