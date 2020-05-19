package services.springdatajpa;

import java.util.*;
import java.util.stream.Collectors;


import dto.AddresDto;
import dto.UserDto;
import error.UserAlreadyExistException;
import javassist.tools.rmi.ObjectNotFoundException;
import lombok.extern.slf4j.Slf4j;
import model.Address;
import model.Role;
import model.User;
import model.VerificationToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import repositories.AddressRepository;
import repositories.RoleRepository;
import repositories.UserRepository;
import repositories.VerificationTokenRepository;
import services.UserService;

/**
 * Created by ch on 2020-05-06
 */
@Slf4j
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private RoleRepository roleRepository;
    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;
    @Autowired
    private AddressRepository addressRepository;
    @Autowired
    private VerificationTokenRepository tokenRepository;
    @Transactional
    @Override
    public User registerNewUserAccount(UserDto userDto, AddresDto addresDto) {
        log.info("Register new User");
        if (emailExists(userDto.getEmail())) {
            System.out.println("Konto z takim eamilem juz istenieje");
            throw new UserAlreadyExistException("Istnieje już konto z takim adresem email: " + userDto.getEmail());
        }
        System.out.println("Konto z takim emailem nie istenieje");
        User user = new User();
        user.setFirstName(userDto.getFirstName());
        user.setLastName(userDto.getLastName());
        //System.out.println(userDto.getPassword());
        //System.out.println(bCryptPasswordEncoder.matches(userDto.getPassword(),bCryptPasswordEncoder.encode(userDto.getPassword())));
        user.setPassword(bCryptPasswordEncoder.encode(userDto.getPassword()));
        user.setEmail(userDto.getEmail());
        user.setRoles(Arrays.asList(roleRepository.findByName("ROLE_USER")));
        if(!addresDto.checkNull()) {
            log.info("Address is not null");
            Address address = new Address();
            address.setStreet(addresDto.getStreet());
            address.setNumberOfHouseOrApartment(addresDto.getNumberOfHouseOrApartment());
            address.setCity(addresDto.getCity());
            address.setProvince(addresDto.getProvince());
            address.setPostcode(addresDto.getPostcode());
            address.setCountry(addresDto.getCountry());
            addressRepository.save(address);
            user.setAddress(address);
        }
        return userRepository.save(user);
    }

    @Override
    public boolean emailExists(String email) {
        return userRepository.findByEmail(email) != null;
    }

    @Override
    public User findByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    @Override
    public User findByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    @Override
    public User getUser(String verificationToken) {
        User user = tokenRepository.findByToken(verificationToken).getUser();
        return user;
    }

    @Override
    public VerificationToken getVerificationToken(String VerificationToken) {
        return tokenRepository.findByToken(VerificationToken);
    }

    @Override
    public void saveRegisteredUser(User user) {
        userRepository.save(user);
    }

    @Override
    public void createVerificationToken(User user, String token) {
        VerificationToken myToken = new VerificationToken(token, user);
        tokenRepository.save(myToken);
    }

    @Override
    public Set<User> findAll() {
        Set<User> users = new HashSet<>();
        userRepository.findAll().forEach(users::add);
        return users;
    }

    @Override
    public User findById(Long id) throws ObjectNotFoundException {
        Optional<User> optionalUser = userRepository.findById(id);

        if (!optionalUser.isPresent()) {
            throw new ObjectNotFoundException("User Not Found. For Id value " + id.toString());
        }

        return optionalUser.get();
    }

    @Override
    public User save(User user) {
        return userRepository.save(user);
    }

    @Override
    public void delete(User user) {
        userRepository.delete(user);
    }

    @Override
    public void deleteById(Long id) {
        userRepository.deleteById(id);
    }
}
