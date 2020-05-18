package services.springdatajpa;

import dto.ReservationDto;
import javassist.tools.rmi.ObjectNotFoundException;
import lombok.extern.slf4j.Slf4j;
import model.Reservation;
import model.RestaurantTable;
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
import java.util.Date;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

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
    public void book(Principal userPrincipal, ReservationDto reservationDto) {
        User user = userService.findByEmail(userPrincipal.getName());
        log.info(reservationDto.getStartHour());
        log.info(reservationDto.getEndHour());
        if(Utils.isValidDate(reservationDto.getDate(),reservationDto.getStartHour())) {
            LocalDate date = Utils.parseDate(reservationDto.getDate());
            LocalTime startHour = Utils.parseHour(reservationDto.getStartHour());
            LocalTime endHour = Utils.parseHour(reservationDto.getEndHour());
            log.info(date.toString());
            log.info(startHour.toString());
            log.info(endHour.toString());
            Optional<RestaurantTable> restaurantTableOptional =restaurantTableService.findFreeTable(date, startHour, endHour, reservationDto.getPersons());
            if (restaurantTableOptional.isPresent()){
                RestaurantTable restaurantTable = restaurantTableOptional.get();

                Reservation reservation = new Reservation();

                reservation.setDate(date);
                reservation.setEndHour(endHour);
                reservation.setStartHour(startHour);
                reservation.setPersons(reservationDto.getPersons());
                reservation.setRestaurantTable(restaurantTable);
                reservation.setUser(user);
                reservation.setStatus("Potwierdzono");

                restaurantTableService.save(restaurantTable);
                userService.save(user);
                this.save(reservation);
            }
            else {
                log.error("Table not found");
                throw new RuntimeException("Table not found");
            }
        }
        else {
            log.error("Date bad format");
            throw new RuntimeException("Bad date format");
        }
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
