package model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by ch on 2020-05-14
 */
@Setter
@Getter
@NoArgsConstructor
@Entity
@Table(name = "restaurant_table")
public class RestaurantTable extends BaseEntity {
    private String number;
    private String floor;
    private int maxNumberOfPeople;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "restaurantTable")
    private Set<Reservation> reservations = new HashSet<>();

    public boolean isFree(LocalDate date, LocalTime startHour, LocalTime endHour) {


        long dateReservation = reservations.stream()
                .filter(reservation -> reservation.getDate().isEqual(date))
                .count();

        if (dateReservation == 0) {
            System.out.println("dateReservation" + dateReservation);
            return true;
        } else {
            return reservations.stream()
                    .filter(reservation -> reservation.getDate().isEqual(date))
                    .allMatch(reservation -> {
                        if (reservation.getStartHour().compareTo(startHour) <= 0 && reservation.getEndHour().compareTo(startHour) > 0) {
                            System.out.println("Start Hour");
                            return false;
                        } else if (reservation.getStartHour().compareTo(endHour) <= 0 && reservation.getEndHour().compareTo(endHour) > 0) {
                            System.out.println("End Hour");
                            return false;
                        } else
                            return true;
                    });
        }

    }
}
