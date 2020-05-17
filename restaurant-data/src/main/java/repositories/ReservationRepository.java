package repositories;

import model.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by ch on 2020-05-16
 */
public interface ReservationRepository extends JpaRepository<Reservation,Long> {
}
