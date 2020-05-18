package services;

import dto.ReservationDto;
import model.Reservation;

import java.security.Principal;

/**
 * Created by ch on 2020-05-16
 */
public interface ReservationService extends CrudService<Reservation,Long> {

    void book(Principal user, ReservationDto reservationDto);
}
