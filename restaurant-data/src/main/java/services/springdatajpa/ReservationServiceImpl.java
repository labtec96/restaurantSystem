package services.springdatajpa;

import dto.ReservationDto;
import javassist.tools.rmi.ObjectNotFoundException;
import lombok.extern.slf4j.Slf4j;
import model.Reservation;
import model.RestaurantTable;
import model.Status;
import model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import repositories.ReservationRepository;
import services.ReservationService;
import services.RestaurantTableService;
import services.UserService;
import util.Utils;

import java.security.Principal;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * Created by ch on 2020-05-16
 */
@Slf4j
@Service
public class ReservationServiceImpl implements ReservationService {

    @Autowired
    ReservationRepository reservationRepository;

    @Autowired
    UserService userService;

    @Autowired
    RestaurantTableService restaurantTableService;

    @Override
    public void book(Principal userPrincipal, Reservation reservation) {
        User user = userService.findByEmail(userPrincipal.getName());
        log.info(reservation.getStartHour().toString());
        log.info(reservation.getEndHour().toString());

        LocalDate date = reservation.getDate();
        LocalTime startHour = reservation.getStartHour();
        LocalTime endHour = reservation.getEndHour();
        Optional<RestaurantTable> restaurantTableOptional = restaurantTableService.findFreeTable(date, startHour, endHour, reservation.getPersons());
        if (restaurantTableOptional.isPresent()) {
            RestaurantTable restaurantTable = restaurantTableOptional.get();

            reservation.setRestaurantTable(restaurantTable);
            reservation.setUser(user);
            reservation.setStatus(Status.WERYFIKOWANA);

            restaurantTableService.save(restaurantTable);
            userService.save(user);
            this.save(reservation);
        } else {
            log.error("Table not found");
            throw new RuntimeException("Table not found");
        }
    }

    @Override
    public Set<Reservation> findAllConfirmed() {
        return this.findAll().stream()
                .filter(reservation -> reservation.getStatus().equals(Status.POTWIERDZONA))
                .collect(Collectors.toSet());
    }

    @Override
    public Set<Reservation> findAllToConfirm() {
        return this.findAll().stream()
                .filter(reservation -> reservation.getStatus().equals(Status.WERYFIKOWANA))
                .collect(Collectors.toSet());
    }

    @Override
    public Set<Reservation> findAllConfirmedForToday() {
        LocalDate today = LocalDate.now();
        return this.findAll().stream()
                .filter(reservation -> reservation.getStatus().equals(Status.POTWIERDZONA))
                .filter(reservation -> reservation.getDate().equals(today))
                .collect(Collectors.toSet());
    }


    @Override
    public void confirmById(Long valueOf) throws ObjectNotFoundException {
        Reservation reservation = this.findById(valueOf);

        reservation.setStatus(Status.POTWIERDZONA);
        this.save(reservation);
    }

    @Override
    public void rejectById(Long valueOf) throws ObjectNotFoundException {
        Reservation reservation = this.findById(valueOf);

        reservation.setStatus(Status.ODRZUCONA);
        this.save(reservation);
    }

    @Override
    public Set<Reservation> findAll() {
        return new HashSet<>(reservationRepository.findAll());
    }

    @Override
    public Reservation findById(Long id) throws ObjectNotFoundException {
        Optional<Reservation> reservationOptional = reservationRepository.findById(id);

        if (!reservationOptional.isPresent()) {
            throw new error.ObjectNotFoundException("Reservation Not Found. For Id value " + id.toString());
        }

        return reservationOptional.get();
    }

    @Override
    public Reservation save(Reservation reservation) {
        return reservationRepository.save(reservation);
    }

    @Override
    public void delete(Reservation reservation) {
        reservationRepository.delete(reservation);
    }

    @Override
    public void deleteById(Long id) {
        reservationRepository.deleteById(id);
    }

}
