package services;

import dto.ReservationDto;
import javassist.tools.rmi.ObjectNotFoundException;
import model.Reservation;

import java.security.Principal;
import java.util.Set;

/**
 * Created by ch on 2020-05-16
 */
public interface ReservationService extends CrudService<Reservation,Long> {

    void book(Principal user, ReservationDto reservationDto);

    void confirmById(Long valueOf) throws ObjectNotFoundException;

    void rejectById(Long valueOf) throws ObjectNotFoundException;

    Set<Reservation> findAllConfirmed();

    Set<Reservation> findAllToConfirm();

    Set<Reservation> findAllConfirmedForToday();
}
