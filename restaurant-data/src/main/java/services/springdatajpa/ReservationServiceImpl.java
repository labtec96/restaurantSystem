package services.springdatajpa;

import javassist.tools.rmi.ObjectNotFoundException;
import lombok.extern.slf4j.Slf4j;
import model.Reservation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import repositories.ReservationRepository;
import services.ReservationService;

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
